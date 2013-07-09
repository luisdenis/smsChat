<?xml version="1.0" encoding="UTF-8"?>
<con:xqueryEntry xmlns:con="http://www.bea.com/wli/sb/resources/config">
    <con:xquery>(:: pragma bea:global-element-parameter parameter="$outputParameters1" element="ns1:OutputParameters" location="../adapter/LoggerDBAdapter/xsd/DBAdapter_sp.xsd" ::)
(:: pragma bea:global-element-return element="ns0:eLoggerResponse" location="../wsdl/Logger.wsdl" ::)

declare namespace ns1 = "http://xmlns.oracle.com/pcbpel/adapter/db/sp/DBAdapter";
declare namespace ns0 = "http://services.tigo.com.gt/Logger/types";
declare namespace xf = "http://tempuri.org/Logger/transformation/LoggerResponse/";

declare function xf:LoggerResponse($outputParameters1 as element(ns1:OutputParameters))
    as element(ns0:eLoggerResponse) {
        &lt;ns0:eLoggerResponse>
            &lt;Code>{ data($outputParameters1/ns1:P_CODE) }&lt;/Code>
        &lt;/ns0:eLoggerResponse>
};

declare variable $outputParameters1 as element(ns1:OutputParameters) external;

xf:LoggerResponse($outputParameters1)</con:xquery>
    <con:dependency location="../adapter/LoggerDBAdapter/xsd/DBAdapter_sp.xsd">
        <con:schema ref="Logger/adapter/LoggerDBAdapter/xsd/DBAdapter_sp"/>
    </con:dependency>
    <con:dependency location="../wsdl/Logger.wsdl">
        <con:wsdl ref="Logger/wsdl/Logger"/>
    </con:dependency>
</con:xqueryEntry>