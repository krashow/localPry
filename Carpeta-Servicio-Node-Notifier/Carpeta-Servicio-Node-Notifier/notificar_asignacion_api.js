// Archivo: notificar_asignacion_api.js (Versión con Plantilla Dinámica y Fix de TypeError)

const express = require('express');
const nodemailer = require('nodemailer');
const bodyParser = require('body-parser');

const app = express();
const PORT = 3000;
app.use((req, res, next) => {
    res.setHeader('Access-Control-Allow-Origin', '*'); 
    res.setHeader('Access-Control-Allow-Methods', 'POST, GET, OPTIONS');
    res.setHeader('Access-Control-Allow-Headers', 'Content-Type');
    next();
});

app.use(bodyParser.json());
const formatDate = (date) => {
    return new Date(date).toLocaleDateString('es-ES', { 
        year: 'numeric', 
        month: 'long', 
        day: 'numeric' 
    });
};
const getColorByPriority = (priorityText) => {
    const p = String(priorityText || '').toLowerCase(); 

    switch (p) {
        case 'alta':
            return '#dc2626';
        case 'media':
            return '#fb923c';
        case 'baja':
            return '#1d4ed8';
        default:
            return '#6b7280'; 
    }
};

app.post('/api/notificar-asignacion', (req, res) => {
    console.log(' Petición de notificación RECIBIDA.');
    let rawPriority = req.body.priority;
    let priorityText = 'Sin prioridad';

    if (rawPriority && typeof rawPriority === 'object' && rawPriority.nombre) {
        priorityText = rawPriority.nombre;
    } else if (typeof rawPriority === 'string') {
        priorityText = rawPriority;
    }

    const today = new Date();
    const incidentData = {
        incidentId: 'N/A',
        assignedTo: 'Técnico', 
        title: 'Incidencia a resolver',
        description: 'No se proporcionó descripción.',
        priority: priorityText,
        reportedBy: 'Administrador',
        assignedToEmail: '', 
        incidentLink: '#',
        creationDate: today.toISOString(), 
        ...req.body,
        priority: priorityText 
    };

    console.log('Datos recibidos y procesados:', incidentData);

    if (!incidentData.assignedToEmail) {
        console.error("❌ ERROR 400: assignedToEmail o datos de incidencia faltantes.");
        return res.status(400).send({ message: "Datos de incidencia o email faltantes." });
    }

    const REMITENTE_EMAIL = 'soporte.magri03@gmail.com';
    const APP_PASSWORD_GMAIL = 'flnf mfdp ucnp eusz'; 
    const DESTINATARIO = incidentData.assignedToEmail;

    const priorityColor = getColorByPriority(incidentData.priority);
    const fechaCreacion = formatDate(incidentData.creationDate);

    const htmlTemplate = `
        <div style="font-family: Arial, sans-serif; max-width: 600px; margin: auto; border: 1px solid #e5e7eb; border-radius: 8px; overflow: hidden;">
            
            <div style="background-color: ${priorityColor}; color: white; padding: 20px 25px; text-align: center;">
                <h1 style="margin: 0; font-size: 24px;">Incidencia Asignada - ${incidentData.incidentId}</h1>
                <p style="margin: 5px 0 0;">Creada el ${fechaCreacion}</p>
            </div>

            <div style="padding: 25px;">
                
                <p style="font-size: 16px;">
                    Hola, **${incidentData.assignedTo}**,
                </p>
                <p style="color: #374151;">
                    Te informamos que se te ha asignado la siguiente incidencia. Por favor, atiende y resuelve este problema en un plazo de **una semana**.
                </p>
                
                <h2 style="color: #1f2937; margin-top: 0; border-bottom: 2px solid #f3f4f6; padding-bottom: 10px;">
                    ${incidentData.title}
                </h2>
                
                <table width="100%" cellspacing="0" cellpadding="5" border="0" style="margin-bottom: 20px;">
                    <tr>
                        <td style="font-weight: bold; color: #4b5563; width: 30%;">ID de Incidencia:</td>
                        <td style="color: #1f2937;">#${incidentData.incidentId}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold; color: #4b5563; width: 30%;">Prioridad:</td>
                        <td>
                            <span style="background-color: ${priorityColor}; color: white; padding: 3px 8px; border-radius: 4px; font-size: 12px; font-weight: bold;">
                                ${incidentData.priority.toUpperCase()}
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold; color: #4b5563;">Reportado por:</td>
                        <td style="color: #1f2937;">Administrador</td>
                    </tr>
                </table>
                
                <div style="background-color: #f9fafb; padding: 15px; border-left: 4px solid ${priorityColor}; margin-bottom: 20px; border-radius: 4px;">
                    <p style="font-weight: bold; color: #1f2937; margin-top: 0;">Descripción:</p>
                    <p style="color: #374151; white-space: pre-wrap;">${incidentData.description}</p>
                </div>
                
                <div style="text-align: center; margin-top: 30px;">
                    <a href="${incidentData.incidentLink}" target="_blank" style="
                        display: inline-block; 
                        padding: 12px 25px; 
                        background-color: #10b981; 
                        color: white; 
                        text-decoration: none; 
                        border-radius: 6px; 
                        font-weight: bold;
                        font-size: 16px;
                    ">Ver Incidencia Completa</a>
                </div>

            </div>

            <div style="background-color: #f3f4f6; padding: 15px 25px; text-align: center; font-size: 12px; color: #6b7280;">
                <p style="margin: 0;">Este es un mensaje automático de Soporte Magriturismo.</p>
                <p style="margin: 5px 0 0;">Por favor, no respondas a este correo.</p>
            </div>
            
        </div>
    `;
    let transporter = nodemailer.createTransport({
        service: 'gmail',
        auth: {
            user: REMITENTE_EMAIL,
            pass: APP_PASSWORD_GMAIL
        }
    });

    let mailOptions = {
        from: `"Soporte Magriturismo" <${REMITENTE_EMAIL}>`,
        to: DESTINATARIO,
        subject: `NUEVA ASIGNACIÓN - [${incidentData.priority}] ${incidentData.incidentId}: ${incidentData.title}`,
        html: htmlTemplate 
    };

    transporter.sendMail(mailOptions, (error, info) => {
        if (error) {
            console.error(' ERROR 500 al enviar la notificación de asignación:', error);
            return res.status(500).send({ message: 'Fallo el envío de correo.', error: error.message });
        } else {
            console.log(' ENVÍO EXITOSO a:', DESTINATARIO, 'ID:', info.messageId);
            return res.status(200).send({ message: 'Notificación enviada con éxito.', messageId: info.messageId });
        }
    });
});





app.post('/api/notificar-seguimiento', (req, res) => {
    console.log(' Petición de notificación de SEGUIMIENTO RECIBIDA.');
    const trackingData = {
        incidentId: 'N/A', 
        status: 'Estado Desconocido',
        priority: 'Sin prioridad',
        assignedTo: 'Desconocido', 
        updateTime: new Date().toLocaleString('es-ES'), 
        linkToTicket: '#',
        involvedParties: [],
        history: [],
        ...req.body 
    };
    const REMITENTE_EMAIL = 'soporte.magri03@gmail.com';
    const APP_PASSWORD_GMAIL = 'flnf mfdp ucnp eusz'; 
    if (!trackingData.involvedParties || trackingData.involvedParties.length === 0) {
        console.error(" ERROR 400: No hay destinatarios definidos para el seguimiento.");
        return res.status(400).send({ message: "Destinatarios faltantes para el seguimiento." });
    }
    const primaryRecipient = trackingData.involvedParties[0].email;
    const ccRecipients = trackingData.involvedParties
                            .slice(1) 
                            .map(p => p.email)
                            .join(', ');
    const involvedHTML = trackingData.involvedParties.map(p => `
        <li style="margin-bottom: 5px;">
            <span style="font-weight: bold; color: #003366;">${p.name}</span> (${p.role})
        </li>
    `).join('');
    const historyHTML = trackingData.history.map(h => `
        <div style="margin-bottom: 15px; border: 1px solid #ddd; padding: 12px; border-radius: 5px; background-color: #f7f7f7;">
            <span style="font-weight: bold; color: #003366;">[${h.time || trackingData.updateTime}] ${h.user || 'Sistema'}:</span><br>
            <span style="color: #444; font-style: italic; white-space: pre-wrap;">${h.comment}</span>
        </div>
    `).join('');
    const headerColor = '#003366'; 
    let transporter = nodemailer.createTransport({
        service: 'gmail',
        auth: {
            user: REMITENTE_EMAIL, 
            pass: APP_PASSWORD_GMAIL 
        }
    });
    let mailOptions = {
        from: `"Soporte Magriturismo" <${REMITENTE_EMAIL}>`, 
        to: primaryRecipient,
        cc: ccRecipients,
        subject: `ACTUALIZACIÓN DE SEGUIMIENTO: ${trackingData.incidentId} - Nuevo Estado: ${trackingData.status}`, 
        html: `
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="utf-8">
                <style>
                    body { font-family: 'Roboto', 'Inter', Arial, sans-serif; line-height: 1.6; color: #333; background-color: #f4f4f9; padding: 20px; }
                    .container { max-width: 650px; margin: 0 auto; background-color: #ffffff; border-radius: 10px; overflow: hidden; box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); }
                    .header { 
                        background-color: ${headerColor}; 
                        color: white; 
                        padding: 25px 30px; 
                        font-size: 24px; 
                        font-weight: 700; 
                        text-align: center; 
                        border-bottom: 5px solid #ffc107;
                    }
                    .content { padding: 30px; }
                    .section-title { font-size: 18px; margin-bottom: 15px; color: #003366; border-bottom: 1px solid #ddd; padding-bottom: 5px; font-weight: 600;}
                    .info-block { background-color: #e6f3ff; padding: 15px; border-radius: 8px; margin-bottom: 25px; border: 1px solid #cce5ff; display: flex; flex-wrap: wrap; }
                    .detail-item { width: 50%; padding: 5px 0; }
                    .detail-label { font-weight: bold; color: #555; }
                    .links { margin-top: 30px; text-align: center; }
                    .links a { 
                        padding: 12px 25px; 
                        background-color: #007bff; 
                        color: white; 
                        border-radius: 5px; 
                        text-decoration: none; 
                        font-weight: bold; 
                        display: inline-block;
                    }
                    .links a:hover { background-color: #0056b3; }
                    .footer { text-align: center; font-size: 12px; color: #888; margin-top: 30px; padding: 20px; border-top: 1px solid #eee; }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        ACTUALIZACIÓN DE SEGUIMIENTO - ${trackingData.incidentId}
                    </div> 
                    <div class="content">
                        <p style="font-size: 16px;">Hola a todos,</p>
                        <p>La incidencia <strong>${trackingData.incidentId}</strong> ha sido actualizada. Este es un resumen del estado actual y los comentarios recientes.</p>
                        <div class="section-title">Resumen de Estado</div>
                        <div class="info-block">
                            <div class="detail-item"><span class="detail-label">Último Estado: </span> <strong>${trackingData.status}</strong></div>
                            <div class="detail-item"><span class="detail-label">Prioridad: </span> ${trackingData.priority}</div>
                            <div class="detail-item"><span class="detail-label">Asignado a: </span> ${trackingData.assignedTo}</div>
                            <div class="detail-item"><span class="detail-label">Hora de Actividad: </span> ${trackingData.updateTime}</div>
                        </div>
                        <div class="section-title">Historial de Comentarios Recientes</div>
                        ${historyHTML}
                        <div class="section-title" style="margin-top: 35px;">Personas en el Seguimiento (CC)</div>
                        <ul style="list-style-type: none; padding-left: 0;">
                            ${involvedHTML}
                        </ul>
                        <div class="links">
                            <a href="${trackingData.linkToTicket}" target="_blank">Ver Ticket Completo y Responder</a>
                        </div>
                    </div>
                    <div class="footer">
                        Recibes esta notificación porque estás involucrado en el seguimiento de la incidencia ${trackingData.incidentId}.
                    </div>
                </div>
            </body>
            </html>
        `
    };
    transporter.sendMail(mailOptions, (error, info) => {
        if (error) {
            console.error(' ERROR 500 al enviar el seguimiento de incidencia:', error);
            return res.status(500).send({ message: 'Fallo el envío de correo de seguimiento.', error: error.message });
        } else {
            console.log(' ENVÍO DE SEGUIMIENTO EXITOSO a:', primaryRecipient, 'y', trackingData.involvedParties.length - 1, 'copias.');
            return res.status(200).send({ message: 'Notificación de seguimiento enviada con éxito.', messageId: info.messageId });
        }
    });
});




app.listen(PORT, () => {
    console.log(`Node Notifier corriendo en http://localhost:${PORT}`);
});