<?xml version="1.0" encoding="UTF-8"?>
<con:xqueryEntry xmlns:con="http://www.bea.com/wli/sb/resources/config">
    <con:xquery><![CDATA[xquery version "1.0" encoding "Cp1252";
(:: pragma  parameter="$body" type="xs:anyType" ::)
(:: pragma  parameter="$fault" type="xs:anyType" ::)
(:: pragma  parameter="$inbound" type="xs:anyType" ::)
(:: pragma  type="xs:anyType" ::)

declare namespace xf = "http://tempuri.org/ErrorHandling/proxy/constructErrorHandlingInput/";

declare function xf:constructErrorHandlingInput($body as element(*),
    $fault as element(*),
    $inbound as element(*))
    as element(*) {
        <errorData>
			<body>{$body}</body>
			<inbound>{$inbound}</inbound>
			<fault>{$fault}</fault>
		</errorData>
};

declare variable $body as element(*) external;
declare variable $fault as element(*) external;
declare variable $inbound as element(*) external;

xf:constructErrorHandlingInput($body,
    $fault,
    $inbound)]]></con:xquery>
</con:xqueryEntry>