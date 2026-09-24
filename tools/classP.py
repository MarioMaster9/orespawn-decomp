import os
import argparse
parser = argparse.ArgumentParser(
                    prog='.class Differ',
                    description='diffs .class files')
parser.add_argument('filename')
args = parser.parse_args()
filename = args.filename

os.system(f'javap -v -p danger/orespawn/{filename} > filename_orig.txt')
os.system(f'javap -v -p orespawn_compiled/danger/orespawn/{filename} > filename.txt')

if os.system == 'posix':
    os.system(f'diff filename_orig.txt filename.txt > filename_diff.txt')
elif os.system == 'nt':
    os.system(f'wsl diff filename_orig.txt filename.txt > filename_diff.txt')