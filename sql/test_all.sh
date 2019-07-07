#!/usr/bin/env bash
scp -r ././../sql/ c3p32g02@sandcastle.cosc.brocku.ca:3p32_group/
#ssh c3p32g02@sandcastle.cosc.brocku.ca 'source reset_data.sh; source generate_dummy_data.sh'