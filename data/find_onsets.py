#!/usr/bin/python

from subprocess import call

call("../onsets/onsets -v */*.wav", shell=True)

