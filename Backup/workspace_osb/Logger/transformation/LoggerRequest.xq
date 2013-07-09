(:: pragma bea:global-element-parameter parameter="$eLoggerRequest1" element="ns0:eLoggerRequest" location="../wsdl/Logger.wsdl" ::)
(:: pragma bea:global-element-return element="ns1:InputParameters" location="../adapter/LoggerDBAdapter/xsd/DBAdapter_sp.xsd" ::)

declare namespace ns1 = "http://xmlns.oracle.com/pcbpel/adapter/db/sp/DBAdapter";
declare namespace ns0 = "http://services.tigo.com.gt/Logger/types";
declare namespace xf = "http://tempuri.org/Logger/transformation/LoggerRequest/";

declare function xf:LoggerRequest($eLoggerRequest1 as element(ns0:eLoggerRequest))
    as element(ns1:InputParameters) {
        <ns1:InputParameters>
            <ns1:P_APPLICATION_ID>{ data($eLoggerRequest1/ApplicationID) }</ns1:P_APPLICATION_ID>
            <ns1:P_ACTION>{ data($eLoggerRequest1/ActionID) }</ns1:P_ACTION>
            <ns1:P_DESCRIPTION>{ data($eLoggerRequest1/Description) }</ns1:P_DESCRIPTION>
            <ns1:P_TIMESTAMP>{ data($eLoggerRequest1/Timestamp) }</ns1:P_TIMESTAMP>
        </ns1:InputParameters>
};

declare variable $eLoggerRequest1 as element(ns0:eLoggerRequest) external;

xf:LoggerRequest($eLoggerRequest1)
