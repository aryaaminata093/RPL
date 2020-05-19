<?php include("config.php"); ?>

<html>
<head>
	<link rel='icon' type='image/png' href='iconrs.png'>
	<title>Rumah Sakit</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
	<div class="content">
	<header>
		<h1>Antrian Rumah Sakit</h1>
		<p>Web ini merupakan simulasi interface untuk pihak rumah sakit (resepsionis) untuk mengontrol antrian yang sedang berlangsung</p>
	</header>

	<form action='nextantrian.php' method='POST' >
		<input class='jadwal' type="number" id='idjadwal' name='idjadwal'placeholder="Id Jadwal"/>
		<input class='tanggal' type="date" id='tanggal' name='tanggal'/>
		<button class='tombol' type='submit' value="Next" name="next">Next Antrian</button>
		
		<p class='note'>*masukan Id Jadwal dan Tanggal Antrian</p>
	</form>

	<br>

	<table border='1' class='tabel'>
	<thead>
		<tr>
			<th>No Antrian</th>
			<th>Tanggal Antrian</th>
			<th>Id Pasien</th>
			<th>Nama Pasien</th>
			<th>Id Jadwal</th>
			<th>Nama Dokter</th>
			<th>Status</th>
		</tr>
	</thead>
	<tbody>

		<?php
		$sql = 'SELECT noantrian, tglantrian, id_pasien, namapasien, id_jadwal, namadokter, statusantrian
				FROM rumahsakit.pasien JOIN rumahsakit.antrian ON idpasien=id_pasien
				JOIN rumahsakit.jadwal ON id_jadwal=idjadwal
				JOIN rumahsakit.dokter ON id_dokter=iddokter';
		$query = mysqli_query($konek, $sql);
		if (!$query) {
			die ('SQL Error: ' . mysqli_error($konek));
		}
		while($antri = mysqli_fetch_array($query)){
			echo '<tr>
			 	<td>'.$antri['noantrian'].'</td>
			 	<td>'.$antri['tglantrian'].'</td>
			 	<td>'.$antri['id_pasien'].'</td>
			 	<td>'.$antri['namapasien'].'</td>
			 	<td>'.$antri['id_jadwal'].'</td>
			 	<td>'.$antri['namadokter'].'</td>
			 	<td>'.$antri['statusantrian'].'</td>
			</tr>';
			}
		?>

	</tbody>
	</table>
	<p class='note'>*Status antrian 1:sudah keluar antrian dan 0:masih di dalam antrian</p>
	</div>
</body>
</html>
