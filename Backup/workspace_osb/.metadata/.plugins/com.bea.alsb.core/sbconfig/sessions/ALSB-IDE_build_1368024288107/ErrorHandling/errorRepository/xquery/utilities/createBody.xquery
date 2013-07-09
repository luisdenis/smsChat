<?xml version="1.0" encoding="UTF-8"?>
<con:xqueryEntry xmlns:con="http://www.bea.com/wli/sb/resources/config">
    <con:xquery>xquery version "1.0" encoding "Cp1252";
(:: pragma  parameter="$myfault" type="xs:anyType" ::)
(:: pragma  type="xs:anyType" ::)

declare namespace xf = "http://tempuri.org/ErrorHandling/errorRepository/createBody/";

declare function xf:createBody($myfault as element(*))
    as element(*) {
        &lt;soapenv:Body xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
        	{$myfault}
        &lt;/soapenv:Body>
};

declare variable $myfault as element(*) external;

xf:createBody($myfault)</con:xquery>
</con:xqueryEntry>