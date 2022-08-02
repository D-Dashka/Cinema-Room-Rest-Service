# Cinema Room Rest Service

### First try at Spring Boot and Rest Serive
<hr>

### JetBrains Academy project

## Description

The aim of this project is to create the cinema service using Spring and Rest that should handle several GET and POST requests to show available ticket, buy and refund tickets and display statistics and display appropriate errors to manage bad requests.

## Endpoints

<code>/seats</code>

	display all avaialable seats at cinema

<code>/purchase</code>

	request number of a row and of a column to buy a ticket and hide it from available seats

<code>/return</code>

	using UUID token to identify ticket and refund it

<code>/stats</code>

	manager can request statistic about cinema (available tickets, total income, the number of purchased tickets) only by proving the correct password
