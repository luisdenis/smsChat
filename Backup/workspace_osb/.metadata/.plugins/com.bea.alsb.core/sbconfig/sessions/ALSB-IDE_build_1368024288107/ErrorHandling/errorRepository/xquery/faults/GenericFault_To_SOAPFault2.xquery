<?xml version="1.0" encoding="UTF-8"?>
<con:xqueryEntry xmlns:con="http://www.bea.com/wli/sb/resources/config">
    <con:xquery><![CDATA[xquery version "1.0" encoding "Cp1252";
(:: pragma  parameter="$body" type="xs:anyType" ::)
(:: pragma  parameter="$inbound" type="xs:anyType" ::)
(:: pragma  parameter="$fault" type="xs:anyType" ::)
(:: pragma  type="xs:anyType" ::)

declare namespace xf = "http://tempuri.org/ErrorHandling/errorRepository/GenericFault_To_SOAPFault/";
declare namespace ctx = "http://www.bea.com/wli/sb/context";
declare namespace tp = "http://www.bea.com/wli/sb/transports";
declare namespace http = "http://www.bea.com/wli/sb/http";
declare namespace soapenv = "http://schemas.xmlsoap.org/soap/envelope/";
declare namespace err = "http://nl.xenta/services/errorRepository";

declare function xf:GenericFault_To_SOAPFault($body as element(*),
    $inbound as element(*),
    $fault as element(*))
    as element(*) {
		<soapenv:Fault>
		<faultcode>soapenv:Server</faultcode>
		(:<faultstring>{data($errorMetadataDetails/err:omschrijving)}</faultstring>:)
		<faultstring>some resource path</faultstring>
		<faultactor>{fn:concat($inbound/ctx:transport/ctx:request/tp:headers/http:Host/text(),"/",$inbound/ctx:transport/ctx:uri/text())}</faultactor>
		   <detail>
				<custom_details2>
				  <payload>{$body, $fault, $inbound}</payload>
				</custom_details2>				
		   </detail>
		</soapenv:Fault>	
};

declare variable $body as element(*) external;
declare variable $inbound as element(*) external;
declare variable $fault as element(*) external;

xf:GenericFault_To_SOAPFault($body,
    $inbound,
    $fault)]]></con:xquery>
</con:xqueryEntry>