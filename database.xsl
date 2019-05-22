<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
   <head>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous" />
            <style>
                table {
                    border: 2px solid black;
                    border-collapse: collapse;
                }
                td, th, tr {
                    border: 1px solid black;
                    text-align: center;
                }
                h2 {
                color: rgb(31, 70, 135);
                font-weight: bold;
                text-align: center;
                }

</style>
</head>
  <body>
  <h2>Teams</h2>
  <table class="table table-hover table-striped" >
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Rank</th>
      <th>First coach</th>
      <th>Analising coach</th>
      <th>Young coach</th>
       <th>Adress</th>
    </tr>
    <xsl:for-each select="chessLeague/teams/team">
    <xsl:sort select="rank" data-type="number" />
    <tr>
      <xsl:if test="@id='WWA'">  
      <td style="color: #516077;"><xsl:value-of select="@id"/></td>
      <td style="color: #516077;"><xsl:value-of select="name"/></td>
      </xsl:if>
        <xsl:if test="@id='PZN'">  
      <td style="color: #4979c1;"><xsl:value-of select="@id"/></td>
      <td style="color: #4979c1;"><xsl:value-of select="name"/></td>
      </xsl:if>
        <xsl:if test="@id='LKS'">  
      <td style="color: #d64646;"><xsl:value-of select="@id"/></td>
      <td style="color: #d64646;"><xsl:value-of select="name"/></td>
      </xsl:if>
        <xsl:if test="@id='GDA'">  
      <td style="color: #12753b;"><xsl:value-of select="@id"/></td>
      <td style="color: #12753b;"><xsl:value-of select="name"/></td>
      </xsl:if>
        <xsl:if test="@id='WLN'">  
      <td style="color: #66b7b4;"><xsl:value-of select="@id"/></td>
      <td style="color: #66b7b4;"><xsl:value-of select="name"/></td>
      </xsl:if>
      <td><xsl:value-of select="rank"/></td>
      <td><xsl:value-of select="staff/first_coach/firstname"/> 
    <xsl:text> </xsl:text>
      <xsl:value-of select="staff/first_coach/lastname"/></td>
      <td><xsl:value-of select="staff/analising_coach/firstname"/>
      <xsl:text> </xsl:text>
      <xsl:value-of select="staff/analising_coach/lastname"/></td>
     <td><xsl:value-of select="staff/young_coach/firstname"/>
    <xsl:text> </xsl:text>
      <xsl:value-of select="staff/young_coach/lastname"/></td>
      <td><xsl:value-of select="address/street"/>
     <xsl:text> </xsl:text>
      <xsl:value-of select="address/city"/>
    <xsl:text> </xsl:text>
      <xsl:value-of select="address/country"/></td>
    </tr>
    </xsl:for-each>
  </table >
  <h2>Players</h2>
  <table class="table table-hover table-striped" >
    <tr >
      <th>ID</th>
      <th>Firstname</th>
      <th>Lastname</th>
      <th>Team</th>
      <th>Elo</th>
      <th>Individual rank</th>
       <th>Nationality</th>
       <th>Wins</th>
       <th>Draws</th>
       <th>Lost</th>
    </tr>
    <xsl:for-each select="chessLeague/players/player">
    <xsl:sort select="individual_rank" data-type="number" />
    <tr>
      <td><xsl:value-of select="@player_id"/></td>
      <td><xsl:value-of select="firstname"/></td>
      <td><xsl:value-of select="lastname"/></td>
      <td><xsl:value-of select="player_team/@refid"/> </td>
      <td> <xsl:value-of select="elo"/></td>
      <td><xsl:value-of select="individual_rank"/></td>
      <td><xsl:value-of select="nationality/@code"/></td>
     <td><xsl:value-of select="games/wins"/></td>
     <td><xsl:value-of select="games/draw"/></td>
     <td><xsl:value-of select="games/lost"/></td>
    </tr>
    </xsl:for-each>
  </table>
  <h2>Marches</h2>
  <table class="table table-hover table-striped" >
    <tr >
      <th>Home Team</th>
      <th>First Table Player</th>
      <th>Second Table Player</th>
      <th>Score</th>
      <th> : </th>
      <th>Score</th>
       <th>Second Table Player</th>
       <th>First Table Player</th>
       <th>Away Team</th>

    </tr>
    <xsl:for-each select="chessLeague/matches/match">
    <tr>
      <td><xsl:value-of select="home_team/teamname/@refid"/></td>
      <td><xsl:value-of select="home_team/first_table_player/@refid"/></td>
      <td><xsl:value-of select="home_team/second_table_player/@refid"/></td>
      <td><xsl:value-of select="home_team/points"/> </td>
      <td>  </td>
      <td><xsl:value-of select="away_team/points"/></td>
     <td><xsl:value-of select="away_team/second_table_player/@refid"/></td>
     <td><xsl:value-of select="away_team/first_table_player/@refid"/></td>
     <td><xsl:value-of select="away_team/teamname/@refid"/></td>
    </tr>
    </xsl:for-each>
  </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet> 