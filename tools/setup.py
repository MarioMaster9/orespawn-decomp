from pathlib import Path

import os

def write_options(output_file, java_home):
	try:
		with open(output_file, "w", encoding="utf-8") as file:
			file.write(f"org.gradle.java.home={java_home}\n")
	except:
		print(f"Unexpected exception when writing to {output_file}")

def main():
	javaHomeFound = False
	javaHome = None
	while (not javaHomeFound):
		javaHome = Path(input("Path to JDK 6: "))

		javac = javaHome / "bin"

		if os.name == "posix":
			javac = javac / "javac"
		elif os.name == "nt":
			javac = javac / "javac.exe"

		if javac.exists():
			print(f"Using Java compiler from: {javac}")
			javaHomeFound = True
		else:
			print(f"Could not find Java compiler from path: {javac}")

	if javaHome is not None:
		project_file = Path("build.gradle")

		if not project_file.exists():
			print("setup.py should be ran from the root of the repository!")
		else:
			gradle_properties = Path("gradle.properties")
			write_options(gradle_properties, javaHome)

if __name__ == "__main__":
	main()