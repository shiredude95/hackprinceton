from django.conf.urls import url

from . import views

urlpatterns = [
    url(r'^gol$', views.room, name='room')
]