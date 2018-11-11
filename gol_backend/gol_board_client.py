from websocket import create_connection
import json, time, threading, requests

SOCKET_URL = "ws://81a36e2b.ngrok.io/ws/chat/gol/"
# SOCKET_URL = "ws://127.0.0.1:8000/ws/chat/gol/"
BOARD_X = 40
BOARD_Y = 10

class GameOfLifeClient():

    def __init__(self, socket_url, size_x, size_y):

        self.gol_board_ob = GameOfLifeBoard(size_x, size_y)
        self.ws = create_connection(socket_url)
    
    def send_updated_board(self):

        self.gol_board_ob.simulate_game()
        board = self.gol_board_ob.get_board()
        devices = get_devices()
        result = {}
        count = 0
        result = []

        for device in devices:

            data = {}
            l = [row[10 * count: 10 * (count + 1)] for row in board]
            data["mac"] = device["mac"]
            data["board"] = [item for sublist in l for item in sublist]
            result.append(data) 
            count += 1

        self.ws.send(json.dumps(result))

    def close(self):

        # Device.objects.all().delete()
        # Event.objects.all().delete()
        self.ws.close()

class GameOfLifeBoard():

    def __init__(self, size_x, size_y):

        self._size_x = size_x
        self._size_y = size_y
        self._cell_buttons = []
        self._init_grid(size_x, size_y)

    def _init_grid(self, size_x, size_y):

        for i in range(size_y):

            self._cell_buttons.append([0] * size_x)

        self._cell_buttons[5][14] = 1
        self._cell_buttons[5][15] = 1
        self._cell_buttons[5][16] = 1

    def _get_user_toggle_events(self):

        result = []
        mac_reversed_indices = get_mac_reversed_indices()
        events = get_events()

        for event in events:

            result.append((event['y'], event['x'] + (10 * mac_reversed_indices[event['mac']])))
            
        requests.post(url = 'http://127.0.0.1:8000/chat/delete_events/')
        return result

    def simulate_game(self):

        buttons_to_toggle = self._get_user_toggle_events()

        for coord in buttons_to_toggle:

            self._cell_toggle(coord)

        # creates list of buttons in grid to toggle
        buttons_to_toggle = []

        for i in range(self._size_y):

            for j in range(self._size_x):

                coord = (i, j)

                # if cell dead and has 3 neighbors, add coordinate to list of coords to toggle
                if self._cell_buttons[i][j] == 0 and self._neighbor_count(i, j) == 3:

                    buttons_to_toggle.append(coord)

                # if cell alive and does not have 2 or 3 neighbors,, add coordinate to list of coords to toggle
                elif self._cell_buttons[i][j] == 1 and self._neighbor_count(i, j) != 3 and self._neighbor_count(i, j) != 2:

                    buttons_to_toggle.append(coord)

        # updates (toggles) the cells on the grid
        for coord in buttons_to_toggle:

            self._cell_toggle(coord)

    def get_board(self):

        return self._cell_buttons

    def _neighbor_count(self, y_coord, x_coord):

        count = 0

        for i in range(y_coord - 1, y_coord + 2):

            for j in range(x_coord - 1, x_coord + 2):

                if i < 0 or j < 0 or i >= self._size_y or j >= self._size_x:

                    continue

                elif (i != y_coord or j != x_coord) and self._cell_buttons[i][j] == 1:

                    count += 1

        return count

    def _cell_toggle(self, coord):

        self._cell_buttons[coord[0]][coord[1]] = self._cell_buttons[coord[0]][coord[1]] ^ 1

def get_mac_reversed_indices():

    result = {}
    count = 0
    devices = get_devices()

    for device in devices:

        result[device['mac']] = count
        count += 1

    return result

def get_devices():

    r = requests.get(url = 'http://127.0.0.1:8000/chat/get_devices/')
    return r.json()

def get_events():

    r = requests.get(url = 'http://127.0.0.1:8000/chat/get_events/')
    return r.json()


def periodic_call(gol_client_ob, frequency):
    
    gol_client_ob.send_updated_board()
    threading.Timer(frequency, periodic_call, [gol_client_ob, frequency]).start()

if __name__ == '__main__':
    
    gol_client_ob = GameOfLifeClient(SOCKET_URL, BOARD_X, BOARD_Y)

    # while len(get_devices()) < 4:

    #     time.sleep(3)

    periodic_call(gol_client_ob, 0.75)
