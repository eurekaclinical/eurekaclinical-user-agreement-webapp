<%--
  #%L
  Eureka! Clinical User Agreement Webapp
  %%
  Copyright (C) 2016 Emory University
  %%
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  
       http://www.apache.org/licenses/LICENSE-2.0
  
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  #L%
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader(
            "Expires", 0); //prevents caching at the proxy server
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <meta name="keywords"
              content="informatics, i2b2, biomedical, clinical research, research, de-identification, clinical data analysis, analytics, medical research, data analysis tool, clinical database, eureka!, eureka, scientific research, temporal patterns, bioinformatics, ontology, ontologies, ontology editor, data mining, etl, cvrg, CardioVascular Research Grid"/>
        <meta name="Description"
              content="A Clinical Analysis Tool for Biomedical Informatics and Data"/>
        <link rel="SHORTCUT ICON"
              href="${pageContext.request.contextPath}/favicon.ico">
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-2.2.4.min.js" type="text/javascript"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="https://cdn.rawgit.com/showdownjs/showdown/1.4.2/dist/showdown.min.js" type="text/javascript"></script>
        <script src="https://assets.eurekaclinical.org/v2.0a1/js/ec.bootbar.js" type="text/javascript"></script>
        <script src="https://assets.eurekaclinical.org/v2.0a1/js/ec.idletimeout.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $(document).idleTimeout({
                    idleTimeLimit: ${pageContext.session.maxInactiveInterval - 30}, //Time out with 30 seconds to spare to make sure the server session doesn't expire first
                    redirectUrl: '${pageContext.request.contextPath}/logout',
                    alertDisplayLimit: 60, // Display 60 seconds before send of session.
                    sessionKeepAliveTimer: ${pageContext.session.maxInactiveInterval - 15} //Send a keep alive signal with 15 seconds to spare.
                });
            });
        </script>
        <title>User Agreement Saved</title>
    </head>
    <body>
        <div class="container-fluid">
            <h1>User Agreement Saved</h1>
            <div id="thankyou" class="alert alert-success" role="alert">Thank you!</div>
        </div>
    </body>
</html>
