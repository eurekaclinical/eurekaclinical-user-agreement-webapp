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
        <link href="//fonts.googleapis.com/css?family=Source+Sans+Pro:400,600,700,400italic,600italic,700italic"
              rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-2.2.4.min.js" type="text/javascript"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="https://cdn.rawgit.com/showdownjs/showdown/1.4.2/dist/showdown.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/ec.bootbar.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/ec.idletimeout.js" type="text/javascript"></script>
        <script type="text/javascript">
		$(document).ready(function (){
		$(document).idleTimeout({
			  idleTimeLimit: ${pageContext.session.maxInactiveInterval - 30}, //Time out with 30 seconds to spare to make sure the server session doesn't expire first
			  redirectUrl: '${pageContext.request.contextPath}/logout',
			  alertDisplayLimit: 60, // Display 60 seconds before send of session.
			  sessionKeepAliveTimer: ${pageContext.session.maxInactiveInterval - 15} //Send a keep alive signal with 15 seconds to spare.
			});
		});
	</script>
        <title>User Agreement Editor</title>
    </head>
    <body>
        <div class="container-fluid">
            <h1>User Agreement Editor</h1>
            <div id="loader">
                <i class="fa fa-refresh fa-spin"></i>
                Loading...
            </div>
            <div id="message">
                <div id="getfailed" class="alert alert-danger" role="alert">Error fetching data use agreement.</div>
                <div id="savefailed" class="alert alert-danger" role="alert"></div>
                <div id="savesucceeded" class="alert alert-success" role="alert">Data use agreement saved successfully.</div>
            </div>
            <form id="theform">
                <div class="panel with-nav-tabs panel-default">
                    <div class="panel-heading">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab1default" data-toggle="tab">Edit</a></li>
                            <li><a href="#preview" data-toggle="tab">Preview</a></li>
                        </ul>
                    </div>
                    <div class="panel-body">
                        <div class="tab-content" style="height: 500px">
                            <div class="tab-pane fade in active" id="tab1default" style="height: 100%">
                                <textarea id="editor" name="editor" oninput="updatePreview()" style="width: 100%; height: 100%; overflow: auto"></textarea>
                            </div>
                            <div class="tab-pane fade" id="preview" style="height: 100%; overflow: auto"></div>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-default" id="saveButton" name="saveButton">Save</button>
            </form>
        </div>
        <script type="text/javascript">
            defaultDua = '# My Data Use Agreement';

            converter = new showdown.Converter();

            setPreview = function () {
                $('#preview').html(converter.makeHtml($('#editor').val()));
            }
            updatePreview = function () {
                setPreview();
                $('#savesucceeded').hide();
                $('#savefailed').hide();
                $('#saveButton').prop('disabled', false);
            }

            $('#theform').hide();
            $('#getfailed').hide();
            $('#savefailed').hide();
            $('#savesucceeded').hide();
            $('#saveButton').prop('disabled', true);
            
            $.ajax({
                url: "${pageContext.request.contextPath}/proxy-resource/useragreements/current",
            }).done(function (data) {
                $('#loader').hide();
                $('#editor').val(JSON.parse(data).text);
                setPreview();
                $('#theform').show();
            }).fail(function (jqXHR) {
                if (jqXHR.status === 404) {
                    $('#loader').hide();
                    $('#editor').val(defaultDua);
                    $('#theform').show();
                    setPreview();
                } else {
                    $('#loader').hide();
                    $('#getfailed').show();
                }
            });

            $('#theform').submit(function (evt) {
                evt.preventDefault();
                $('#saveButton').prop('disabled', true);
                $.ajax({
                    url: "${pageContext.request.contextPath}/proxy-resource/useragreements",
                    method: "POST",
                    data: JSON.stringify({text: $('#editor').val()})
                }).done(function () {
                    $('#savesucceeded').show();
                }).fail(function (jqXHR) {
                    if (jqXHR.status === 403) {
                        $('#savefailed').html('Save failed. Admin role is required.');
                    } else {
                        $('#savefailed').html('Save failed.');
                    }
                    $('#savefailed').show();
                });
            });

        </script>
    </body>
</html>
