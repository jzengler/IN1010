from random import randrange




navn = ["Anne", "Bjarne", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliet","Kilo","Lima","Mike","November","Oscar", "Papa", "Quebec","Jonny","Vidar"];

with open("mangenavn.txt", "w+") as f:
    for n in navn:
        for i in range(1, randrange(10, 50)):
            f.write(n+"\n")
