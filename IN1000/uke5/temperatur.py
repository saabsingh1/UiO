minfil = open("max_temperatures_per_month.csv")
mt_ordbok = {}
def min_ordbok (minfil): 
    for linje in minfil:
        biter = linje.split(",")
        maned = biter[0]
        temperatur = float(biter[1])
        mt_ordbok [maned] = float(temperatur)
    return (mt_ordbok)

print(min_ordbok(minfil))

temp_dager = open("max_daily_temperature_2018.csv")
temp_ordbok = {}
def sammenligning (min_ordbok, temp_dager):
    for linje in temp_dager: 
        biter = linje.split(",")
        maned = biter[0]
        dag = int(biter[1])
        temperatur = float(biter[2])
        temp_ordbok[maned,dag] = (temperatur) 
        if mt_ordbok[maned] < temperatur: 
            print("Den nye varmerekorden er satt:", dag, maned, temperatur, "grader.", "Den gamlevarmerekorden var på:", mt_ordbok[maned], "grader.")
            mt_ordbok[maned] = temperatur
    
    
    return mt_ordbok
 
print(sammenligning(mt_ordbok,temp_dager))

ny_fil = open("makstemp.csv", "w")
def skriveut (mt_ordbok, ny_fil):
    for maned in mt_ordbok:
        ny_fil.write(str(maned) + "," + str(mt_ordbok[maned]) + "\n")
        

skriveut(mt_ordbok, ny_fil)




