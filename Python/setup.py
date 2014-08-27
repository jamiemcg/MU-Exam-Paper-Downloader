from cx_Freeze import setup, Executable

exe=Executable(
     script="exam_papers.py",
     icon="icon.ico"
     )
includefiles=[]
includes=[]
excludes=[]
packages=[]
setup(

     version = "0.1",
     description = "A simple command line program to download files from the NUIM Library website",
     name = "NUIM Exam Paper Downloader",
     options = {'build_exe': {'excludes':excludes,'packages':packages,'include_files':includefiles}},
     executables = [exe]
     )