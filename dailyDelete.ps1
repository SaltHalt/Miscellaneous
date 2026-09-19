$logMessage
try {
    $logMessage = (Get-Date).ToString() + "`n";
    $today = Get-Date
    $folder = Get-Item 'D:\Downloads\1_delete\'
    try{
        Set-ItemProperty -Path $folder.FullName -Name LastWriteTime -Value $today
        $logMessage += "= Successfully changed folder's LastWriteTime`n"
    } catch {
        $logMessage += "! Couldn't change folder's LastWriteTime`n"
    }

    Get-ChildItem $folder | Where-Object { $_.LastWriteTime.AddHours(48) -lt $today } | Remove-ItemSafely
    
    #Get-ChildItem $folder | Where-Object { $_.LastWriteTime.AddHours(48) -lt $today } | echo

    $logMessage += "- Successfully deleted old files`n" +
                  "------------------------------------------------------------------"

    $logMessage | Out-File -FilePath "D:\Config\Scripts\dailyDeleteLogs.txt" -Append
    echo $logMessage
    exit 0
}
catch {
    $formatstring = "{0} : {1}`n{2}`n" +
                    "  + CategoryInfo        : {3}`n" +
                    "  + FullyQualifiedErrorId : {4}"
    $fields = $_.InvocationInfo.MyCommand.Name,
              $_.Exception.Message,
              $_.InvocationInfo.PositionMessage,
              $_.CategoryInfo.ToString(),
              $_.FullyQualifiedErrorId

    $logMessage += $formatstring -f $fields + "`n" +
                  "------------------------------------------------------------------"

    $logMessage | Out-File -FilePath "D:\Config\Scripts\dailyDeleteLogs.txt" -Append
    echo "Error"
    echo $logMessage
    exit 1
}
   
<# Test
$today = '2023-12-05 22:00:00'
$files = Get-ChildItem $folder
$files[0].LastWriteTime = '2023-12-05 14:30:00'
$files[1].LastWriteTime = '2023-12-04 21:59:59'
$files[2].LastWriteTime = '2023-12-05 22:00:01'
$files[3].LastWriteTime = '2022-12-05 06:30:00'
$files[4].LastWriteTime = '2023-12-04 15:30:22'
#Only files 0 and 2 should be left over.
#>