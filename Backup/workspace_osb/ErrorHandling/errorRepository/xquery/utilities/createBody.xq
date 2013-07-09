xquery version "1.0" encoding "Cp1252";
(:: pragma  parameter="$myfault" type="xs:anyType" ::)
(:: pragma  type="xs:anyType" ::)

declare namespace xf = "http://tempuri.org/ErrorHandling/errorRepository/createBody/";

declare function xf:createBody($myfault as element(*))
    as element(*) {
        <soapenv:Body xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
        	{$myfault}
        </soapenv:Body>
};

declare variable $myfault as element(*) external;

xf:createBody($myfault)