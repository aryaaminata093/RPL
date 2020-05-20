<?php
include("koneksi.php");
if(isset($_POST['next'])){

	$idjadwal = $_POST['idjadwal'];
	$tanggal = $_POST['tanggal'];

	$sql = "UPDATE dataantrian
		SET statusantrian = '1'
		WHERE jadwalid='$idjadwal' AND tglantrian='$tanggal' 
		AND noantrian=(SELECT min(noantrian) FROM dataantrian WHERE statusantrian='0')";
	$query = mysqli_query($konek, $sql);

	if( $query==TRUE ) {
		echo "Next antrian berhasil! :)";
		//header('Location: antrian.php');
		
	} 
	else {
		echo "Next antrian gagal! :( \r\n";
		die ('SQL Error: ' . mysqli_error($konek));
	}


} else {
	die("Akses dilarang...");
}
?>
