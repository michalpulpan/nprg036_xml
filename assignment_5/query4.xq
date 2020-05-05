(:/xquery/query4.xq: Vybere produkty, které je nutné doobjednat - dostupnost na nějakém skladu je nižší jak 50ks od těch :)

for $prods in fn:doc('data.xml')//product
let $warehouses := $prods/quantity/location
return
if (some $l in $warehouses satisfies $l/text() < 50)
then <toOder code="{ $prods/code/text() }"/>
else <fine code="{ $prods/code/text() }"/>