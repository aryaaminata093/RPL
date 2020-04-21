# API TESTING

## A Place for api testing and learning and stuff

### Steps

1. Install requirement di terminal
```bash
sudo apt update
sudo apt install python3-pip && sudo apt install python-pip
sudo apt install python3-pymysql
pip3 install flask-bcrypt && pip3 install flask-bcrypt
pip3 install flask-jwt-extended
sudo pip install --user pipenv
pipenv install
pipenv shell
```
2. Buat database di mysql dengan nama `api_test`
3. Di file config.py, 

4. Lakukan migrasi
```bash
python3 migrate.py db init
python3 migrate.py db migrate
python3 migrate.py db upgrade
```
PS : Setiap ada perubahan di file resources/Model.py harus dilakukan migrasi `migrate` dan `upgrade` untuk mengupdate database

5. Programnya di run
```bash
python3 run.py
```


###Current progress..
1. Sign Up from /api/signup througth POST with required json data : username, email, password

2. Log in from /api/login througth POST with required json data : email, password

###To-Do
1. Clean up Login Code
2. The rest..