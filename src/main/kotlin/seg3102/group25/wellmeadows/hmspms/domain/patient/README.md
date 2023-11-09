<h1>Patient Sub Domain</h1>
<h2>Core</h2>
<p>This subdomain is the bounded context for all in and out patients undergoing Treatment(Core domain) at a hospital.
</p>

<h3>Rationale</h3>
<p>This is a core subdomain as it will need application specific functionality implemented and it is a necessary prerequisite
to have a well functioning Patient Management domain. </p>

<h3>Relevant Functional Requirements</h3>
<ul>
<li>
F1. The PMS shall allow any member of the medical staff to register patients
</li>
<li>
F2. The PMS shall allow medical staff members to update the registration information of patients
</li>
<li>
F4. The PMS shall allow Charge Nurses to admit patients to their division.
</li>
<li>
F5. The PMS shall allow Charge Nurses to request a patient admission to a division
</li>
<li>
F6. The PMS shall allow Charge Nurses to admit patients subject to admission requests, in their
division
</li>
<li>
F7. The PMS shall allow Charge Nurses to dis-charge patients from their division.
</li>
<li>
F8. The PMS shall allow doctors to prescribe medication to their patients
</li>
<li>
F9. The PMS shall prevent admitting a patient to a division with a complete status
</li>
<li>
F11. The PMS shall allow any medical staff to consult the file of any patient
</li>
</ul>

<h3>Relevant Use Cases</h3>
<ul>
<li>
Register Patient 
</li>
<li>
Consult Patient File
</li>
<li>
Update Patient File 
</li>
<li>
Admit patient 
</li>
<li>
Request Patient Admission
</li>
<li>
Admit patient from
request list
</li>
<li>
Discharge Patient
</li>
<li>
Prescribe Medication
</li>
</ul>