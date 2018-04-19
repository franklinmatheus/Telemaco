function disableSeasons () {
	var selectSerie = document.getElementById("serieName");
	var selectSeason = document.getElementById("seasonNumber");
	
	if (selectSerie.value != "") 
		selectSeason.disabled = false;
}

function enableAllInputs () {
	var selectSeason = document.getElementById("seasonNumber");
	
	var epName = document.getElementById("epName");
	var epNumber = document.getElementById("epNumber");
	var epSynopsis = document.getElementById("epSynopsis");
	var epTime = document.getElementById("epTime");
	
	if (selectSeason.value != "") {
		epName.disabled = false;
		epNumber.disabled = false;
		epSynopsis.disabled = false;
		epTime.disabled = false;
	}	
}

//function alertSerieChosen (idSerie) {
//	alert(idSerie)
//}

function changeImgNameFile () {
	var inputFile = document.getElementById("imgFile");
	var outputFile = document.getElementById("txtImgFile");


	if (inputFile.value == "Nenhum arquivo selecionado" || inputFile.value == "")
		outputFile.innerHTML = "Nenhum arquivo selecionado";
	else 
		outputFile.innerHTML = inputFile.value.split('\\').pop();
}