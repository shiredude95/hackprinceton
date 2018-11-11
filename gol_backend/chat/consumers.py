from channels.generic.websocket import AsyncWebsocketConsumer
from .models import Device, Event
import json

class ChatConsumer(AsyncWebsocketConsumer):

    async def connect(self):
        # self.room_name = self.scope['url_route']['kwargs']['room_name']
        self.room_name = 'gol'
        self.room_group_name = 'chat_%s' % self.room_name

        # Join room group
        await self.channel_layer.group_add(
            self.room_group_name,
            self.channel_name
        )

        await self.accept()

    async def disconnect(self, close_code):
        # Leave room group
        await self.channel_layer.group_discard(
            self.room_group_name,
            self.channel_name
        )

    # Receive message from WebSocket
    async def receive(self, text_data):

        text_data_json = json.loads(text_data)

        if 'connect' in text_data_json:

            Device.objects.create(mac = text_data_json['mac'])

        elif 'disconnect' in text_data_json:

            Device.objects.get(mac = text_data_json['mac']).delete()

        elif 'event' in text_data_json:

            Event.objects.create(mac = text_data_json['mac'], x = text_data_json['x'], y = text_data_json['y'])

        else:

            # Send message to room group
            await self.channel_layer.group_send(self.room_group_name, {'type': 'chat_message', "data" : str(text_data_json)})

    # Receive message from room group
    async def chat_message(self, event):

        # print(event)
        # message = event['message']
        # Send message to WebSocket
        print(event)
        print('--------------------')
        await self.send(text_data=str(event))