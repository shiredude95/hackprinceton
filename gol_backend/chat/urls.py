from django.conf.urls import url

from . import views

urlpatterns = [
    url(r'^gol$', views.room, name='room'),
    url(r'^get_devices/', views.get_devices),
    url(r'^get_events/', views.get_events),
]