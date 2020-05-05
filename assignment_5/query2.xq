(:/xquery/query2.xq: Nashlukuje pracovní vybavení dle kategorí a vypíše je:)

for $cat in fn:distinct-values(fn:doc('data.xml')//item_eqp/@category)
let $eqp := fn:doc('data.xml')//item_eqp[@category = $cat]
order by $cat ascending
return
<group category="{ $cat }">
{

    for $e in $eqp
    order by $e/@item_eqp_id
    return <item SN="{ $e/SN/text() }"/>

}
</group>