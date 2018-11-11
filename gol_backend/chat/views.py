from django.shortcuts import render
from django.utils.safestring import mark_safe
from .models import Device, Event
from rest_framework.decorators import api_view
from django.http import JsonResponse
import json

# Create your views here.
# def index(request):
#     return render(request, 'chat/index.html', {})

# def room(request, room_name):
#     return render(request, 'chat/room.html', {
#         'room_name_json': mark_safe(json.dumps(room_name))
#     })

def room(request):
    return render(request, 'chat/room.html', {})


@api_view(['GET'])
def get_devices(request):

    return JsonResponse(list(Device.objects.all().values()), safe = False)

@api_view(['GET'])
def get_events(request):

    return JsonResponse(list(Event.objects.all().values()), safe = False)

@api_view(['POST'])
def delete_events(request):

    Event.objects.all().delete()
    return JsonResponse({'success' : 'True'}, safe = False)

