<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
    <xsl:output
            method="html"
            version="4.0"
            encoding="UTF-8"
            indent="yes"
            doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
            doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
    />
	
    <xsl:template match="/"> <!-- Schéma tabulky -->
        <html>
            <head>
                <title>E-Shop Data Viewer</title>
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"/> <!---->
            </head>
            <body>
				
                <h2>Table of Employees</h2>
                <table class="table table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Firstname</th>
                            <th scope="col">Surname</th>
                            <th scope="col">SSN</th>
                            <th scope="col">Position</th>
                            <th scope="col">Education</th>
                            <th scope="col">Contract to</th>
                            <th scope="col">Received equipment</th>
                            <th scope="col">Assigned warehouse</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:apply-templates select="webshop/employees/employee"/>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>


    <xsl:template match="employees/employee"> <!-- Obsah tabulky -->
        <xsl:variable name="counter" select="count(preceding-sibling::employee)"/> 
        <xsl:variable name="employeeID" select="@id_num"/> 
        <tr>
            <td scope="row">
                <xsl:value-of select="$counter"/>
            </td>
            <td>
                <xsl:apply-templates select="firstname"/>
            </td>
            <td>
                <xsl:apply-templates select="surname"/>
            </td>
            <td>
                <xsl:apply-templates select="ssn"/>
            </td>
            <td>
                <xsl:apply-templates select="position"/>
            </td>
            <td>
                <xsl:apply-templates select="education"/>
            </td>
            <td>
                <xsl:call-template name="contract">
                    <xsl:with-param name="contract_id" select="contract/@contract_id"/>
                </xsl:call-template>            
            </td>
            <td>
                <xsl:call-template name="received_equipment">
                    <xsl:with-param name="employee_id" select="@id_num"/>
                </xsl:call-template>
            </td>
            <td>
                <xsl:copy>
                    <xsl:apply-templates select="//warehouse[@responsible_employee = $employeeID]" mode="warehouseMode"/>
                </xsl:copy>
            </td>
        </tr>
    </xsl:template>

    <xsl:template name="contract"> <!-- Data o smlouvach -->
        <xsl:param name="contract_id"/>
        <xsl:variable name="contract_from" select="//contract[@contract_id = $contract_id]/from"/>
        <xsl:variable name="contract_to" select="//contract[@contract_id = $contract_id]/to"/>
        <xsl:variable name="contract_type" select="//contract[@contract_id = $contract_id]/type"/>
        <xsl:text>id: </xsl:text>
        <xsl:value-of select="$contract_id"/> <br/>
        <xsl:text>to: </xsl:text>
        
        <xsl:choose> 
            <xsl:when test="$contract_to !=null">
                    <xsl:value-of select="$contract_to" />
            </xsl:when>
            <xsl:otherwise>  <!-- Smlouva na dobu neurčitou -->
                    <i>Unknown</i>
            </xsl:otherwise>
        </xsl:choose>
        <br/>

        <xsl:text>type: </xsl:text>
        <xsl:value-of select="$contract_type"/> <br/>
    </xsl:template>

    <xsl:template name="received_equipment">  <!-- Data o vybavení zamestnance -->
        <xsl:param name="employee_id"/>
        <xsl:for-each select="//employee[@id_num = $employee_id]/equipment_received/item">
            <xsl:sort select="given_date" order="ascending"/>

            <xsl:text>item id: </xsl:text>
            <xsl:value-of select="@id_item"/>
            
                <br/>
            <xsl:text>returned: </xsl:text>

            <xsl:choose>
                <xsl:when test="contains(./@returned, 'no')">  <!-- Doposud nevrácené vybavení -->
                    <span style="color: red;"><xsl:value-of select="./@returned"/></span>
                </xsl:when>
                <xsl:otherwise> <!-- Vrácené vybavení -->
                    <span style="color: green;"><xsl:value-of select="./@returned"/></span> 
                </xsl:otherwise>
            </xsl:choose>

            <br/>
            <xsl:text>given date: </xsl:text>
            <xsl:value-of select="given_date"/>

            <xsl:if test="position() != last()">
                <br/><br/>
            </xsl:if>
        </xsl:for-each>

    </xsl:template>

    <xsl:template match="warehouse" mode="warehouseMode">  <!-- Prirazeny sklad k zamestnanci -->
            
            <xsl:text>id: </xsl:text>
            <xsl:value-of select="@warehouse_id"/>
            <br/>
            <xsl:text>location: </xsl:text>
            <xsl:value-of select="name_warhouse"/>
    </xsl:template>




</xsl:stylesheet>