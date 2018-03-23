; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

#define MyAppName "Gestion Biblioth�que"
#define MyAppVersion "1.0"
#define MyAppPublisher "Mahmoud B. Amor"
#define MyAppURL "linkedin.com/in/mahmoud-ben-amor-276563128/"
#define MyAppExeName "Gestion Biblioth�que.exe"

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{73358D7A-AFF8-48DE-A9DC-955D3B6FA790}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
;AppVerName={#MyAppName} {#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
AppSupportURL={#MyAppURL}
AppUpdatesURL={#MyAppURL}
DefaultDirName={pf}\{#MyAppName}
DisableProgramGroupPage=yes
OutputDir=C:\Users\ASUS\Desktop
OutputBaseFilename=Setup GB 1.0
SetupIconFile=D:\The Lab\2eme annee\Projet Java\ic_launcher.ico
Compression=lzma
SolidCompression=yes

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"
Name: "french"; MessagesFile: "compiler:Languages\French.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "D:\The Lab\2eme annee\Projet Java\Version Finale\GestionBibliotheque\dist\Gestion Biblioth�que.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "D:\The Lab\2eme annee\Projet Java\Version Finale\GestionBibliotheque\dist\lib\*"; DestDir: "{app}\lib"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "D:\The Lab\2eme annee\Projet Java\Version Finale\GestionBibliotheque\BaseEtudiants.txt"; DestDir: "{app}"; Permissions: users-modify;Flags: ignoreversion
Source: "D:\The Lab\2eme annee\Projet Java\Version Finale\GestionBibliotheque\BaseLivres.txt"; DestDir: "{app}"; Permissions: users-modify;Flags: ignoreversion
Source: "D:\The Lab\2eme annee\Projet Java\Version Finale\GestionBibliotheque\BasePret.txt"; DestDir: "{app}"; Permissions: users-modify;Flags: ignoreversion
Source: "D:\The Lab\2eme annee\Projet Java\Version Finale\GestionBibliotheque\BaseProfesseurs.txt"; DestDir: "{app}"; Permissions: users-modify;Flags: ignoreversion
Source: "D:\The Lab\2eme annee\Projet Java\Version Finale\GestionBibliotheque\config.txt"; DestDir: "{app}"; Permissions: users-modify;Flags: ignoreversion
Source: "D:\The Lab\2eme annee\Projet Java\Version Finale\GestionBibliotheque\incrementEtudiants.txt"; DestDir: "{app}"; Permissions: users-modify;Flags: ignoreversion
Source: "D:\The Lab\2eme annee\Projet Java\Version Finale\GestionBibliotheque\incrementLivres.txt"; DestDir: "{app}"; Permissions: users-modify;Flags: ignoreversion
Source: "D:\The Lab\2eme annee\Projet Java\Version Finale\GestionBibliotheque\incrementProfs.txt"; DestDir: "{app}"; Permissions: users-modify;Flags: ignoreversion
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{commonprograms}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"
Name: "{commondesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; Tasks: desktopicon

[Run]
Filename: "{app}\{#MyAppExeName}"; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"; Flags: nowait postinstall skipifsilent
