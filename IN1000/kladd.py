def sjekk_om_fyord(setning, fyord, synonym_liste):
    for ord in setning.split():
        if not ord == fyord: 
            return True 
            
        else:
            return False
            

print(sjekk_om_fyord("spis masse godsaker", "godsaker", [["saft","lemonade"],["snacks","snop","godsaker"],["mye","masse"]]))
