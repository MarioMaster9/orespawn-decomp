import hashlib
import shutil
from os import listdir
from os.path import isfile, join
import zipfile

try:
    shutil.rmtree('./orespawn_compiled')
except FileNotFoundError:
    pass

jar_folder = "build/libs"
jar = "modid-1.0.jar"

shutil.copyfile(f'{jar_folder}/{jar}', f'./{jar}')

with zipfile.ZipFile(f'./{jar}', 'r') as zip_ref:
    zip_ref.extractall('./orespawn_compiled')
onlyfiles = [f for f in listdir("danger/orespawn") if isfile(join("danger/orespawn", f))]


for filename in onlyfiles:
    hash1 = ""
    hash2 = ""
    try:
        with open(f"danger/orespawn/{filename}", 'rb') as f:
            hash1 = hashlib.md5(f.read()).hexdigest()
        with open(f"orespawn_compiled/danger/orespawn/{filename}", 'rb') as f:
            hash2 = hashlib.md5(f.read()).hexdigest()
        if hash1 == hash2:
            print(f'{filename} MATCHING')
        else:
            print(f'{filename} NONMATCHING')
    except FileNotFoundError:
        print(f'{filename} MISSING')