Proiect PAO - Audio Library

In cadrul aplicatiei de consola se pot realiza urmatoarele operatii
* register
	Primeste 2 argumente
	*username
	*parola
* login
	Primeste 2 argumente
	*username
	*parola
nrPagini
	Primeste 1 argument
	*nr
	Se schimba numarul maxim de elemente afisate pe o pagina
logout
	Nu primeste argumente
promote
	Primeste 1 argument
	*username
	Un administrator poate oferi rol de administrator altui user
create song
	Primeste 3 argumente
	*nume
	*autor
	*an lansare
create playlist
	Primeste 1 argumente
	*nume
	Creaza un playlist asociat cu userul curent
list  playlist
	Nu are argumente
	Listeaza toate playlisturile unui user
add
	Primeste minim 3 argumente
	*byID/byName in functie de modul in care se doreste sa fie adresat playlistul
	*id/nume Playlisy
	*unul sau mai multe id-uri de melodii separate prin " "
search
	Primeste 2 argumente
	*name/author in functie de criteriul dorit de cautare
	*prefix
	Se vor afisa toate cantecele pentru care numele/autorul incepe cu prefixul dat
export playlist
	Primeste 3 argumente
	*byID/byName
	*id/nume playlist
	*JSON/CSV/STEVE
import playlist
	Primeste 2 argumente
	*JSON/CSV/STEVE
	*path
audit
	Primeste 1 argument
	*username
	Afiseaza istoricul comenzilor unui utilizator dat
run
	Primeste 1 argument
	*id audit
	Ruleaza comanda din audit