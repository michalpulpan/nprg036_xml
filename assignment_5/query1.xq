(:/xquery/query1.xq: Vybere rodná čísla zaměstanců jejichž plat je nad průměrem :)

let $employees := fn:doc('data.xml')//employee
for $e in $employees
where ($e/salary/text() > fn:avg($employees/salary/text()))
return $e/ssn/text()