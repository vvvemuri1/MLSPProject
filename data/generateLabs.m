function generateLabs(style, name)
filename = fullfile(style, name);

% load onsets file
onsets = load(strcat(filename, '.onsets'));

% load notes file
notes_file = fopen(fullfile('notes', strcat(name, '.txt')));
if(notes_file == -1)
    disp(fullfile('notes', strcat(name, '.txt')));
end
notes = fgetl(notes_file);
while (~feof(notes_file))
    notes = [notes; {fgetl(notes_file)}];
end
fclose(notes_file);

[wav, fs] = wavread(strcat(filename, '.wav'));
onsets = [onsets; length(wav)];
clear wav;
% convert sample to second
onsets = onsets./fs;

output_dir = fullfile('lab', style);

if(~exist(output_dir, 'dir'))
    mkdir(output_dir);
end

output_file = fopen(fullfile(output_dir, strcat(strrep(regexprep(name,'[^\w'']',''), '''', ''), '.lab')), 'w');
fprintf(output_file, '#\n');
if(isempty(onsets))
    fprintf(name);
end

for i = 1 : length(onsets)
    if(i == 1)
        notes{i} = 'ssil';
    elseif( i == length(onsets))
        notes{i} = 'pau';
    elseif(strcmp(notes{i}, 'PAU'))
        notes{i} = 'pau';
    else
        notes{i} = strrep(notes{i}, '#', 's');
        notes{i} = strcat(notes{i}, 'N');
    end
    fprintf(output_file, '%f 125 %s\n', onsets(i), notes{i});   
end
fclose(output_file);