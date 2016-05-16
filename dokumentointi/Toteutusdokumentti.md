**Ohjelman yleisrakenne**

Maaston luontia varten olen tehnyt oman generaattorin, jossa käytetään Perlinkohinaa maaston luomiseen. Olen toteuttanut myös timantti-neliö-algoritmin (diamond square algorith), mutta sitä ei käytetä lopullisessa maastogeneraattorissa.

Timantti-neliö-algoritmi luo maastoa niin, että ensin annetaan jotkut korkeusarvot maaston nurkkapisteille ja sitten neliö- ja timanttiaskeleita vuorotellen lasketaan arvot kaikille muille pisteille. Neliöaskeleella otetaan kaikki pisteet, joille on määrätty arvo ja näistä pisteistä muodostetaan neliöitä. Näiden neliöiden keskipisteille määrätään arvo ottamalla neliön nurkkapisteiden keskiarvo ja lisäämällä tähän jokin satunnaisarvo. Timanttiaskeleella muodostetaan nurkallaan seisovia neliöitä, eli timantteja, ja lasketaan niiden keskipisteille arvot samoin kuin neliöaskeleessa.

Perlinkohina taas toimii niin, että luodaan taulukko satunnaisvektoreita, joiden voi ajatella sijaitsevan tietyissä pisteissä maastossa. Nyt mille tahansa pisteelle näiden välissä voi laskea arvon pistettä lähimpien neljän satunnaisvektorin ja vektorien sijaintien ja pisteen sijainnin välisten vektorien avulla. Näin saadaan aina sama arvo tietylle pisteelle, sillä satunnaisvektorit pidetään muistissa.

Generaattoriin talletetaan useita päällekkäisiä Perlinkohinoita, joiden avulla maaston pisteille lasketaan arvot. Useilla Perlinkohinoilla maastoon saadaan eri mittaluokan muotoja, suurempia ja pienenpiä. Haluttu osa maastosta luodaan yhdellä kertaa. Maasto annetaan korkeusarvojen taulukkona.

Taulukkoon tallennettu maasto pitää saada myös muihin muotoihin graafista esitystä varten. Tähän tarkoitukseen on Maailma-luokka, jossa korkeusarvojen taulukko muutetaan mm. vektoriesitykseksi ja kolmioiduksi pinnaksi, ja maastoon lisätään värit ja valonlähteestä aiheutuvat valot ja varjot.

Grafiikkaluokat auttavat maaston visualisoinnissa. RGB-luokka auttaa värien määrittelyssä ja itse Grafiikka-luokka hoitaa maaston piirtämisen ruudulle.

Laskennassa tarvitaan vektoreita, kolmioita ja kvaternioita, joilla on omat luokkansa ja niissä erilaisia metodeja laskutoimituksille.

Olen toteuttanut myös muutaman tietorakenteen, mutta niitä ei tarvita tässä maastogeneraattorissa.

**Aika- ja tilavaativuudet**

Maastossa koostuu blokeista, joita on n x n kappaletta, ja jokaisen blokin sisällä on m x m pistettä. Kokonaisuudessaan maaston koko on siis mn x mn pistettä, mutta kerralla generoidaan ja näytetään vain pieni osa kokonaismaastosta, ja tämän näytettävän maaston koko on (k x m)^2, missä k on blokkien määrä näytettävässä maastossa ja se on pieni kokonaisluku k << n. Generoidun maaston pisteiden lisäksi pitää muistissa pitää montaa perlinkohinan generoimiseen tarvittavaa taulukkoa, joiden koot ovat n^2, (2n)^2, (4n)^2, (8n)^2 ja (16n)^2, eli yhteensä 341n^2. Muistissa tarvitsee siis pitää 341n^2 + (k x m)^2, eli tilavaativuus on O(n^2 + m^2), kun taas timantti-neliö-algoritmilla generoidulla maastolla on kaikki pisteet pidettävä muistissa, joten tilavaativuus on O((n x m)^2), joka on suurempi kuin O(n^2 + m^2). Esimerkiksi jos n = 100 ja m = 100, on tilavaativuuden ero noin 30 kertainen.

Timantti-neliö-algoritmin aikavaativuus on yksinkertaisesti O((n*m)^2), sillä jokaiselle pisteelle lasketaan arvo kerran ja kaikki kerralla. Suurille maastoille tässä menisi paljon aikaa, eikä timantti-neliö-algoritmi sovellukaan suurien maastojen tekemiseen myöskään siksi, että maastoon muodostuu tällä algortimilla ei-haluttuja kulmia, jos sen tekee kovin suurelle maastolle.

Omalla generaattorillani maastoa luodaan pienissä osissa ja vain tarvittaessa. Generaattorin luomiseen menee ensin aikaa O(n^2), ja tämä tehdään vain kerran ohjelman ajon alussa. Tämän jälkeen maaston luonti toistetaan aina kun maastossa halutaan liikkua, eli useita kertoja ohjelman aikana. Maaston luontiin aikaa kuluu O(m^2), sillä jokaisen pisteen luomiseen kuluu vakioaika.

**Puutteet ja parannusehdotukset**

Sekä tila- että aikavaativuutta olisi hyvä parantaa. Tilavaativuus on joka tapauksessa O(n^2 + m^2), mutta vakiokertoimia voisi saada pienemmäksi esim. vähemmällä määrä Perlinkohinoita tai saamalla Perlinkohinataulukot pienemmiksi. Muistin kanssa näyttää tulevan ongelmia suurilla n:n arvoilla, vaikka teoreettisesti pitäisi olla olla mahdollista tehdä n = 2000 kokoista maastoa. Jossain vaiheessa kun testasin ohjelmaa suurella n:n arvolla ja liikuin maastossa pitkään, jumittui maasto jossain vaiheessa koko maaston reunalla. En tiedä mistä tämä johtuu, mutta vika vaikuttaisi olevan muistin loppumisessa, ei maastonluonnissa itsessään.

Oikeastaan itse algoritmin maastonluonti onnistuu nopeasti suurillakin m:n arvoilla, mutta ongelmaksi tulee grafiikan hitaus. Grafiikka ei ole itse algoritmin kannalta mitenkään oleellinen, mutta tulosten graafinen esittäminen on tärkeä osa maastogeneraattoria. Nyt maastogeneraattori toimii jouhevasti vain m < 100 arvoilla, mutta tämä ei siis johdu algoritmin maastonluonnista, vaan piirtämisen hitaudesta. Grafiikan kanssa on myös renderointiongelmia, mutta ne ovat melko epäoleellisia.

Luodun maaston luonnollisuudessa olisi parannettavaa. Tällä hetkellä maastosta tulee liian säännöllistä. Suuret maaston muodot, eli vuoret toistuvat liian säännöllisesti joka blokissa. Epäsäännöllisempään maastoon pitäisi toteutusta muuttaa huomattavasti. Maastosta voisi myös saada vaihtelevampaa tälläkin toteutuksella niin, että eri blokeissa korkeuserot olisivat erilaisia, mutta sitä en ehtinyt toteuttaa.

**Lähteet**

https://en.wikipedia.org/wiki/Diamond-square_algorithm
https://en.wikipedia.org/wiki/Perlin_noise
