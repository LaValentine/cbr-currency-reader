FROM mcr.microsoft.com/mssql/server:2019-latest
ENV MYSQL_ROOT_PASSWORD ''
ENV ACCEPT_EULA Y
ENV MSSQL_SA_PASSWORD SaPassword111

RUN /opt/mssql/bin/sqlservr & sleep 10 && /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P SaPassword111 -Q "CREATE DATABASE cbrCurrencyReader"
