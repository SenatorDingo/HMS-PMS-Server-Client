<h1>Security Sub Domain</h1>
<h2>Generic</h2>
<p>The security sub domain will be used for authentication of users when logging in to the system and assigning privileges
to a user upon log-in. It will be also be responsible for authentication of user access and login credentials during cross
domain operations.
</p>

<h3>Rationale</h3>
<p> This domain has been assigned as a generic subdomain as it is a pre-existing feature of many present
applications and is readily available off the shelf. </p>

<h3>Relevant Functional Requirements</h3>
<ul>
<li>
F10. The PMS shall allow adding new Users
</li>
<li>
F12. The PMS shall authenticate all access based on the User identification and Password
</li>
<li>
F13. The PMS shall log any access to a patient file with the identifier of the staff member who
initiated the access.
</li>
</ul>

<h3>Relevant Use Cases</h3>
<ul>
<li>
Register Staff
</li>
<li>
Staff log in 
</li>
<li>
Staff log out 
</li>
</ul>