"""
Requirement:
	Write a program in Python or Java that counts backwards from 100 to 1 and prints: “Agile” if the number is divisible by 5, “Software” if the number is divisible by 3, “Testing” if the number is divisible by both, or prints just the number if none of those cases are true. 
	
"""

for divNum in range(100, 1, -1):
	if (divNum%3 == 0):
		print("Agile")
	elif (divNum%5 == 0):
		print("Software")
	elif ((divNum%3 == 0) and (divNum%5 == 0)):
		print("Testing")
	else:
		print(divNum)