(:/xquery/query5.xq: Zobrazí všechny doposud nevrácené zaměstnanecké vybavení :)

declare function local:equipment($employee, $item) as element()*
{
	let $name := fn:concat($employee/firstName/text(), " ", $employee/surname/text())
    return
    	<p>{ "Name: ", $name, " equipement: ", $item/eqp_name/text(), " SN: ", $item/sn/text() }</p>
};

let $items := fn:doc('equipement.xml')//item_eqp
for $e in fn:doc('data.xml')//employee
for $itm in $e/equipment_received/item
let $employee_eqp := //item_eqp[@item_eqp_id=$itm/@id_item]
where $itm[@returned="no"]

return
element employee {
text { local:equipment($e, $employee_eqp)}
}
























