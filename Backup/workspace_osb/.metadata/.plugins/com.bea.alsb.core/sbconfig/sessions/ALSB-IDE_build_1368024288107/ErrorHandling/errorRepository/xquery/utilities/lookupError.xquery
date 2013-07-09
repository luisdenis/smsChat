<?xml version="1.0" encoding="UTF-8"?>
<con:xqueryEntry xmlns:con="http://www.bea.com/wli/sb/resources/config">
    <con:xquery>(:: pragma bea:global-element-parameter parameter="$errorList" element="err:service_error_handling" location="../../xsd/ErrorRepository.xsd" ::)
(:: pragma bea:global-element-return element="ns0:error" location="../../xsd/ErrorRepository.xsd" ::)

declare namespace err = "http://nl.xenta/services/errorRepository";
declare namespace xf = "http://nl.xenta/services/faults/lookupError/";

declare function xf:lookupError2($errorCode as xs:string,
    $errorList as element(err:service_error_handling))
    as element(err:error) {
    
		if(string-length($errorList/err:error[err:code=$errorCode]) > 0)
   		then (
			let $resolvedError := $errorList/err:error[err:code=$errorCode]
	   		return $resolvedError
   		) else (
   			let $resolvedError := $errorList/err:error[err:code='DEFAULT']
   			return $resolvedError
   		)    

};

declare variable $errorCode as xs:string external;
declare variable $errorList as element(err:service_error_handling) external;

xf:lookupError2($errorCode,
    $errorList)</con:xquery>
    <con:dependency location="../../xsd/ErrorRepository.xsd">
        <con:schema ref="ErrorHandling/errorRepository/xsd/ErrorRepository"/>
    </con:dependency>
</con:xqueryEntry>