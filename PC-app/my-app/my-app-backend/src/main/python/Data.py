from __future__ import print_function
import win32gui 
import uiautomation as auto
import time
import re
import csv
from os import system
import json
import Hash_table

class app:
    def __init__(self, name, duration, subn, subd):
        self.name = name
        self.duration = duration
        self.subn = subn
        self.subd = subd    

active_window_name = ""
timestamp = {}
process_time={}
app_ht = Hash_table.Hash_table()   ##equivalente a timestamp
time_ht = Hash_table.Hash_table()   ##equivalente a process_time

def get_active_window():
    window = win32gui.GetForegroundWindow()
    _active_window_name = win32gui.GetWindowText(window)
    return _active_window_name


while True:
    try:
        name_split = None
        previous_site = ""
        new_window_name = get_active_window()    

        if new_window_name == '':
            continue

        if active_window_name != new_window_name:
            print(active_window_name)
            activity_name = active_window_name

          
        name_split = new_window_name.split('-')
        app_info = app(name_split[-1].strip(),0,name_split[0].strip(), 0) 
        app_ht.remove(app_info.name)
        app_ht.insert(app_info.name, int(time.time())) 
        timestamp[app_info.name] = int(time.time())
        time.sleep(1)

#        if app_info.name not in process_time.keys():
#            process_time[app_info.name] = 0

## implementacion de la tabla hash 1
        if time_ht.find(app_info.name) == -1:
            time_ht.insert(app_info.name,0)
            process_time[app_info.name] = 0

#        if app_info.subn not in sub_process_time.keys():
#            sub_process_time[app_info.subn] = 0


## implementacion de la tabla hash 2
        sum = time_ht.find(app_info.name)
        time_ht.remove(app_info.name)
        time_ht.insert(app_info.name, sum + int(time.time())- app_ht.find(app_info.name))
        process_time[app_info.name] = process_time[app_info.name]  + int(time.time())- app_ht.find(app_info.name)


#        process_time[app_info.name] = process_time[app_info.name]+int(time.time())-timestamp[app_info.name]


        json_info = json.dumps(process_time)
        print(json_info)

        json_info = re.sub("\\\\", "", json_info)
        json_info = json.loads(json_info)

#from dictionary to json       
        with open('data.json', 'w') as outfile:
            json.dump(json_info, outfile)

#from dictionary to csv   
        with open('csvdata.csv','w') as f:
            w = csv.DictWriter(f, process_time.keys())
            w.writeheader()
            w.writerow(process_time)

#from dictionary to csv  
        with open('csvdata1.csv', 'w', newline='') as csvfile:
            fieldnames = ['name', 'Duration']
            writer = csv.DictWriter(csvfile, fieldnames=fieldnames)

            writer.writeheader()
            for key in process_time:
                writer.writerow({'name': key, 'Duration': process_time[key]})
        
    except:
        pass


