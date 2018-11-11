from websocket import create_connection
import json, time, threading, requests

# SOCKET_URL = "ws://dc89592a.ngrok.io/ws/chat/gol/"
SOCKET_URL = "ws://127.0.0.1:8000/ws/chat/gol/"
BOARD_X = 20
BOARD_Y = 20

class GameOfLifeClient():

    def __init__(self, socket_url, size_x, size_y):

        self.gol_board_ob = GameOfLifeBoard(size_x, size_y)
        self.ws = create_connection(socket_url)
    
    def send_updated_board(self):

        self.gol_board_ob.simulate_game()
        board = self.gol_board_ob.get_board()
        devices = get_devices()
        result = []
        count = 0

        for device in devices:

            data = {}
            data['mac'] = device['mac']
            x_incr = int(str(bin(count))[2:][0])
            y_incr = 0

            try:

                y_incr = int(str(bin(count))[2:][1])

            except:

                pass

            data['board'] = [row[10 * x_incr: 10 * (x_incr + 1)] for row in board[10 * y_incr: 10 * (y_incr + 1)]]
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

            self._cell_buttons.append([False] * size_x)

    def _get_user_toggle_events(self):

        result = []
        mac_reversed_indices = get_mac_reversed_indices()
        events = get_events()

        for event in events:

            x_incr = int(str(bin(mac_reversed_indices[event['mac']]))[2:][0])
            y_incr = 0

            try:

                y_incr = int(str(bin(mac_reversed_indices[event['mac']]))[2:][1])

            except:

                pass

            result.append((event['y'] + 10 * y_incr, event['x'] + 10 * x_incr))
            Event.objects.get(id = event['id']).delete()

        return result


    def simulate_game(self):

        buttons_to_toggle = self._get_user_toggle_events()

        for coord in buttons_to_toggle:

            self._cell_toggle(coord)

        # creates list of buttons in grid to toggle
        buttons_to_toggle = []

        for i in range(self._size_x):

            for j in range(self._size_x):

                coord = (i, j)

                # if cell dead and has 3 neighbors, add coordinate to list of coords to toggle
                if self._cell_buttons[i][j] == False and self._neighbor_count(i, j) == 3:

                    buttons_to_toggle.append(coord)

                # if cell alive and does not have 2 or 3 neighbors,, add coordinate to list of coords to toggle
                elif self._cell_buttons[i][j] == True and self._neighbor_count(i, j) != 3 and self._neighbor_count(i, j) != 2:

                    buttons_to_toggle.append(coord)

        # updates (toggles) the cells on the grid
        for coord in buttons_to_toggle:

            self._cell_toggle(coord)

    def get_board(self):

        return self._cell_buttons

    def _neighbor_count(self, x_coord, y_coord):

        count = 0

        for i in range(x_coord - 1, x_coord + 2):

            for j in range(y_coord - 1, y_coord + 2):

                if i < 0 or j < 0 or i >= self._size_y or j >= self._size_x:

                    continue

                elif (i != x_coord or j != y_coord) and self._cell_buttons[i][j] == True:

                    count += 1

        return count

    def _cell_toggle(self, coord):

        self._cell_buttons[coord[0]][coord[1]] = not self._cell_buttons[coord[0]][coord[1]]

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

    while len(get_devices()) < 4:

        time.sleep(3)

    periodic_call(gol_client_ob, 0.1)
