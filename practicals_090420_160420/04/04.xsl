<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  version="1.0">
    <xsl:output method="html" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Real Estate Report</title>
            </head>
            <body>
                <h2>Table of Properties</h2>
                <table>
                    <tr>
                        <th>Property ID</th>
                        <th>Property Name</th>
                        <th>Owner Id</th>
                    </tr>
                    <xsl:apply-templates select=".//property"/>
                </table>
                
                <h2>List of Flats</h2>
                <ol>
                    <xsl:apply-templates select=".//flat"/>
                </ol>

            </body>
        </html>
    </xsl:template>

    <xsl:template match="properties/property">
        <tr>
            <td>
                <xsl:apply-templates select="@idProperty"/>
            </td>
            <td>
                <xsl:apply-templates select="name"/>
            </td>
            <td>
                <xsl:apply-templates select="@ownerRef"/>
            </td>
        </tr>
    </xsl:template>

    <xsl:template match="flats/flat">
        <xsl:element name="li">
            <xsl:attribute name="id">
                <xsl:text>flat-</xsl:text>
                <xsl:value-of select="@idFlat"/>
            </xsl:attribute>
            <xsl:text>Flat </xsl:text>
            <xsl:value-of select="@idFlat"/>
            <xsl:text>: </xsl:text>
            <xsl:value-of select="name"/>
        </xsl:element>
    </xsl:template>


</xsl:stylesheet>