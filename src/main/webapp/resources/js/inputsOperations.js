function disableSeasons () {
	var selectSerie = document.getElementById("serieName");
	var selectSeason = document.getElementById("seasonsOptions");
	
	if (selectSerie.value != "") 
		selectSeason.disabled = false;
}

function enableAllInputs () {
	var selectSeason = document.getElementById("seasonsOptions");
	
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

function alertSerieChosen (idSerie) {
	alert(idSerie)
}
