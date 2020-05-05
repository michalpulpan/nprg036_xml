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
	
    <xsl:template match="/">
        <html>
            <head>
                <title>Real Estate Report</title>
                <style>
                    th { background-color: #AAAAAA; }
                    tr.even td { background-color: #DDDDDD; }
                </style>
            </head>
            <body>
				
                <h2>Table of Properties</h2>
                <table>
                    <thead>
                        <tr>
                            <th>num</th>
                            <th>Property ID</th>
                            <th>Property Name</th>
                            <th>Owner name</th>
                            <th>Flat References</th>
                            <th>Properties</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:apply-templates select="real-estate/properties/property"/>
                    </tbody>
                </table>
                
                <h2>List of Flats</h2>
                <ol>
                    <xsl:apply-templates select="real-estate/flats/flat"/>
                </ol>
                
            </body>
        </html>
    </xsl:template>
	
    <xsl:template match="properties/property">
		<!--<xsl:variable name="propertyId" select="@idProperty"/>-->
        <xsl:variable name="propertyId" select="@idProperty"/>
        <xsl:variable name="counter" select=" count(preceding-sibling::property)"/> <!-- https://stackoverflow.com/questions/17630574/how-to-count-the-number-of-same-immediate-preceding-siblings-in-xsl/17630830 -->
        <tr>
            <xsl:if test="($counter mod 2)=0">
                <xsl:attribute name="class">even</xsl:attribute>
            </xsl:if>
            <td>
                <xsl:value-of select="$counter"/>
            </td>
            <td>
                <xsl:apply-templates select="$propertyId"/>
            </td>
            <td>
                <xsl:apply-templates select="name"/>
            </td>
            <td>
                <xsl:call-template name="propOwnerName">
                    <xsl:with-param name="owner_id" select="@ownerRef"/>
                </xsl:call-template>
            </td>
            <td>
                <ul>
                    <xsl:apply-templates select="//flat[@propertyRef = $propertyId]" mode="flatOfProperty"/>
                </ul>
            </td>
            <td>

                <xsl:for-each select="./features/feature"> <!--slide 27-->
                    <xsl:sort select="text()" order="ascending"/> <!-- https://www.w3schools.com/xml/xsl_sort.asp -->
                    <xsl:value-of select="."/>
                    <xsl:if test="position() != last()">
                        <xsl:text>, </xsl:text>
                    </xsl:if>
                </xsl:for-each>

            </td>
        </tr>
    </xsl:template>

    <xsl:template name="propOwnerName">
        <xsl:param name="ownerId" select="@ownerRef"/>
        <xsl:variable name="ownerName" select="//owner[@idOwner = $ownerId]/name"/>
        <xsl:if test="$ownerName">
            <xsl:value-of select="$ownerName"/>
        </xsl:if>
        <xsl:if test="not($ownerName)">
			<i>Unknown</i>
		</xsl:if>
    </xsl:template>
    
    <xsl:template match="flats/flat">
        <xsl:element name="li">
            <xsl:attribute name="id">
                <xsl:value-of select="concat('flat-', @idFlat)"/>
            </xsl:attribute>
            <xsl:text>Flat </xsl:text>
            <xsl:value-of select="@idFlat"/>
            <xsl:text>: </xsl:text>
            <xsl:value-of select="name"/>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="flat" mode="flatOfProperty">
        <li>
            <xsl:element name="a">
                <xsl:attribute name="href">#flat-<xsl:value-of select="@idFlat"/></xsl:attribute>
                <xsl:value-of select="name"/>
            </xsl:element>
        </li>
    </xsl:template>
    
</xsl:stylesheet>