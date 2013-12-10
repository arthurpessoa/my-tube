<%-- 
    Document   : uploadVideo
    Created on : 09/12/2013, 09:17:41
    Author     : Arthur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MyTube 3.0 - Upload Video</title>
    </head>
    <body>
        <div style="text-align: center;">
        <a href="index.html">[Home]</a> <a href="uploadVideo.jsp">[Upload de Vídeos]</a> <a href="index.html">[Download de Vídeos]</a>
        <h3>Upload de Video</h3>
        </div>
    <body>
        
        <form action="ServletUpload" method="post" enctype="multipart/form-data">            
            Selecione o vídeo a ser enviado:<br>
            <input type="file" name="arquivo"/><br>
            Descrição: <br>
            <textarea name="description" ROWS="5" style="width:100%;"></textarea>
            <input type="submit" name="enviar" value="Enviar meu video!" />
        </form>
    </body>
</body>
</html>
