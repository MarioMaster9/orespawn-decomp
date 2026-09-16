import shutil
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