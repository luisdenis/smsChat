(:: pragma bea:global-element-parameter parameter="$outputParameters1" element="ns1:OutputParameters" location="../adapter/LoggerDBAdapter/xsd/DBAdapter_sp.xsd" ::)
(:: pragma bea:global-element-return element="ns0:eLoggerResponse" location="../wsdl/Logger.wsdl" ::)

declare namespace ns1 = "http://xmlns.oracle.com/pcbpel/adapter/db/sp/DBAdapter";
declare namespace ns0 = "http://services.tigo.com.gt/Logger/types";
declare namespace xf = "http://tempuri.org/Logger/transformation/LoggerResponse/";

declare function xf:LoggerResponse($outputParameters1 as element(ns1:OutputParameters))
    as element(ns0:eLoggerResponse) {
        <ns0:eLoggerResponse>
            <Code>{ data($outputParameters1/ns1:P_CODE) }</Code>
        </ns0:eLoggerResponse>
};

declare variable $outputParameters1 as element(ns1:OutputParameters) external;

xf:LoggerResponse($outputParameters1)
