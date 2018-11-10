from django.conf.urls import url

from . import consumers

# websocket_urlpatterns = [
#     url(r'^ws/chat/(?P<room_name>[^/]+)/$', consumers.ChatConsumer),
# ]

websocket_urlpatterns = [
    url(r'^ws/chat/gol', consumers.ChatConsumer),
]