#!usr/bin/env python3
#LOTS OF REPEATED CODE

import _thread
import os
from datetime import date
from urllib.request import urlopen

print("\n\n\n\nNUIM Exam Paper Downloader")
print("--------------------------\n\n")

start_year = date.today().year
end_year = 2004

autu = "Autumn"
janu= "January"
summ = "Summer"
main_ = "Main"
repe = "Repeat"

url = "http://www.nuim.ie/expert2/downloads//"

def main():
    print("What module(s) would you like to download?")
    print("[Enter the modules separated by commas]\n")

    modules = input().upper().split(",")
    repeat = input("\nShould I download repeat exam papers [y/n]? ").upper()
    print()
    
    if repeat == "N" or repeat == "NO":
        repeat = False #Don't download repeats
    else:
        repeat = True

    for module in modules:
        module = module.strip()
        if not os.path.exists(module):
            os.makedirs(module)
        download(module, repeat)
        print() #Clear line between each module


"""
    Downloads the specified exam paper to the current directory (that of this python script)
"""
def download(module, repeat):
    """fileNameOnline = String.valueOf(year) + "-" + module + "-"+ jan + ".pdf";
    String fileName = module + " " + String.valueOf(year) + " " + jan + ".pdf";"""

    year = start_year
    while year > end_year:
        fileAddress = str(year) + "-" + module + "-" + janu + ".pdf"
        fileName = module + " " + str(year) + " " + janu + ".pdf"

        exists = False

        try:
            file = urlopen(url + fileAddress)
            exists = True
        except:
            pass

        if exists:
            with open(os.path.join(module, fileName),'wb') as fileLocal:
                fileLocal.write(file.read())
            print("Saved " + module + " " + str(year) + " " + janu + "\t\t" +  str(round(float(file.info()['Content-length'])/1024, 2)) + "KB")
        
        ###

        fileAddress = str(year) + "-" + module + "-" + summ + ".pdf"
        fileName = module + " " + str(year) + " " + summ + ".pdf"

        exists = False

        try:
            file = urlopen(url + fileAddress)
            exists = True
        except:
            pass

        if exists:
            with open(os.path.join(module, fileName),'wb') as fileLocal:
                fileLocal.write(file.read())
            print("Saved " + module + " " + str(year) + " " + summ + "\t\t" +  str(round(float(file.info()['Content-length'])/1024, 2)) + "KB")

        ###

        if repeat:
            fileAddress = str(year) + "-" + module + "-" + autu + ".pdf"
            fileName = module + " " + str(year) + " " + autu + ".pdf"

            exists = False

            try:
                file = urlopen(url + fileAddress)
                exists = True
            except:
                pass

            if exists:
                with open(os.path.join(module, fileName),'wb') as fileLocal:
                    fileLocal.write(file.read())
                print("Saved " + module + " " + str(year) + " " + autu + "\t\t" +  str(round(float(file.info()['Content-length'])/1024, 2)) + "KB")

            if year <= 2007:
                fileAddress = str(year) + "-" + module + "-" + repe + ".pdf"
                fileName = module + " " + str(year) + " " + repe + ".pdf"

                exists = False

                try:
                    file = urlopen(url + fileAddress)
                    exists = True
                except:
                    pass

                if exists:
                    with open(os.path.join(module, fileName),'wb') as fileLocal:
                        fileLocal.write(file.read())
                    print("Saved " + module + " " + str(year) + " " + repe + "\t\t" +  str(round(float(file.info()['Content-length'])/1024, 2)) + "KB")

        fileAddress = str(year) + "-" + module + "-" + main_ + ".pdf"
        fileName = module + " " + str(year) + " " + main_ + ".pdf"

        exists = False

        try:
            file = urlopen(url + fileAddress)
            exists = True
        except:
            pass

        if exists:
            with open(os.path.join(module, fileName),'wb') as fileLocal:
                fileLocal.write(file.read())
            print("Saved " + module + " " + str(year) + " " + main_ + "\t\t" +  str(round(float(file.info()['Content-length'])/1024, 2)) + "KB")

        year -= 1

if __name__ == '__main__':
    main()