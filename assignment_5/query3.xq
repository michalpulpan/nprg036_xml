(:/xquery/query3.xq: vyvytvoří tabulku všech dostupných produktů na eshopu (skladové zásoby čerpe z data.xml a produkty dostupné na ehsopu z productsEshop.xml) :)

<table>
<tr>
<th>Id</th><th>Name</th><th>Code</th><th>EAN</th><th>Price</th><th>Currency</th><th>Stock</th><th>Description</th>
</tr>
{
let $counter := 0
for $products in fn:doc('productsEshop.xml')//prod
let $prodData := fn:doc('data.xml')//product[code/text() = $products/code/text()]
let $stock := fn:sum($prodData/quantity/location/text())
return 
    <tr>
        <td>COUNTER</td>
        <td>{$products/prod_name/text()}</td>
        <td>{$products/code/text()}</td>
        <td>{$products/EAN/text()}</td>
        <td>{$products/price/text()}</td>
        <td>{$products/currency/text()}</td>
        <td>{
            
            if ($stock > 0)
            then <span style="color:green"> {$stock} </span>
            else <span style="color:red"> {$stock} </span>
            }</td>
        <td>{$products/prod_desc/text()}</td>
    </tr>
}
</table>