var idRiproduzione = "";

// $(document).ready(function () {
//            $("form#riproduzione").submit(function () {
	function riproduci(){
	var codiceSessione = $("#codiceSessioneInputId").val();
	var idPlaylist = $("#idPlaylistInputId").val();
	var idAlbum = $("#idAlbumInputId").val();
	
	if(!isBlank(idAlbum) && !isBlank(idPlaylist)){
		console.log("Impossibile valorizzare entrambi i campi");
	return false;
    }
	if(isBlank(idAlbum) && isBlank(idPlaylist)){
		console.log("Valorizzare almeno un campo");
	return false;
    }
	
	var [raccoltaId, isAlbum] = getRaccolta();
	var tipoRaccolta = isAlbum? "album" : "playlist";
	
	var urlPath = "/riproduzione/"+tipoRaccolta+"/"+raccoltaId;

	
	var parametri = "codiceSessione="+codiceSessione+",raccoltaId"+raccoltaId;

	ajaxSetupTokenInHeader(); 
	
	doCall('POST', urlPath, parametri, function(risposta){
		
		idRiproduzione = risposta.idRiproduzione;
		buildSongsTable(risposta);
		
	}, function (){
		console.log("Chiamata Fallita");
	});
	
	return false;
            
 }

            
function doCall(typeRequest, urlPath, parametri, callbackOnSuccess, callbackOnError){

     console.log("Inizio chiamata: ", urlPath);

     $.ajax({
         type: typeRequest, // Metodo di chiamata da effettuare
         url: urlPath, // Url da chiamare
         data: parametri, //Eventuali parametri da passare NB: si posso scrivere anche in questo {idFromHtml: valore}
         success: callbackOnSuccess,
         error: callbackOnError
     });
 }
 
 
function mostraRiproduzione(show){
		if(show){
			$("form#riproduzione").hide();
			$("#musicaInRiproduzione").show();
			$("#comandiRiproduzione").show();
		}
		else{
			$("#comandiRiproduzione").hide() ;
			$("#musicaInRiproduzione").hide();
			$("form#riproduzione").show();
		}
	}
 
 
function getTokenFromForm() {
		return $("#codiceSessioneInputId").val();
}



 function ajaxSetupTokenInHeader(){
		$.ajaxSetup({
	        headers: {
	            'Content-Type': 'application/json',
	            'Accept': 'application/json',
	            "codiceSessione": getTokenFromForm()
	        }
	    });
}

 
function getRaccolta(){
	var idPlaylist = $("#idPlaylistInputId").val();
	var idAlbum = $("#idAlbumInputId").val();

	if (!isBlank(idAlbum)) {
		return [idAlbum, true];
	} else if (!isBlank(idPlaylist)) {
		return [idPlaylist, false];
	}
}



function isBlank(str) {
    return (!str || /^\s*$/.test(str));
}


function buildSongsTable(json){
	var table = $("#musicaInRiproduzione");
	table.empty();
	
	var tipoRaccolta = json["playlist"] == null? "album" : "playlist";
	var idBranoInAscolto = json["brano"].id;
	
	var brani = json[tipoRaccolta]["braniDTO"];
	
	var tableHead = "<th> ";
	if(tipoRaccolta == "album"){
		tableHead += json["album"].titolo+" "+json["album"].dataUscita ;
	} else if(tipoRaccolta == "playlist"){
		tableHead += json["playlist"].titolo +" "+json["playlist"]["utenteLoginDTO"].username ;
	}
	
	tableHead += " <th>";
	table.append(tableHead)
	
	for(var i = 0; i < brani.length; i++){
		id = brani[i].id
		titolo = brani[i].traccia
		if(tipoRaccolta == "album"){
		album = json["album"].titolo;
		artista = json["album"]["autore"].nome +" "+ json["album"]["autore"].cognome;
		}else if(tipoRaccolta=="playlist"){
			album = brani[i]["album"].titolo;
			artista = brani[i]["album"]["autore"].nome+" "+brani[i]["album"]["autore"].cognome;
		}
		
		var tableRow ="<tr><td>";
		if(id == idBranoInAscolto){
			tableRow += " <b>&#9658;";
		}
		
		tableRow += titolo + ", " + album + ", " + artista + "</td></tr>";
		
		if(id == idBranoInAscolto){
			tableRow += " </b>";
		}
		tableRow += "</td></tr>"
		table.append(tableRow);
	}
	
	mostraRiproduzione(true);
}
	

function cambiaBrano(next){
		
		var [raccoltaId, isAlbum] = getRaccolta();
		var path = "";
		if(isAlbum){
			path += "album/";
		}
		else{
			path += "playlist/";
		}
		path += raccoltaId;
		if(next){
			path += "/play";
		}
		else{
			path += "/playPrevious";
		}
		
		$.ajax({
		  url: path,
		  type:'POST',
		  context: document.body
		}).success(function() {
			console.log("brano cambiato");
			riproduci();
		});
		
		
	}

	function indietro(){
		mostraRiproduzione(false);
		return true;
	
	}
	
function cancellaRiproduzione(){

	var path = "/riproduzione/" + idRiproduzione;
	
	doCall('DELETE', path, {}, function(){

		mostraRiproduzione(false);
		
	}, function (){
		console.log("Chiamata Fallita");
	});
	
}