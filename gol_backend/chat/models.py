from django.db import models

# Create your models here.
class Device(models.Model):

    mac = models.CharField(max_length=50)

    def __str__(self):
        return '%s : %s' % (self.id, self.mac)

