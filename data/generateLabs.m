function generateLabs(style, name)
filename = fullfile(style, name);

onsets = load(strcat(filename, '.onsets'));
notes_file = fopen(fullfile('notes', strcat(name, '.txt')));
notes = fgetl(notes_file);
while (~feof(notes_file))
    notes = [notes; {fgetl(notes_file)}];
end
% notes = fscanf(notes_file, '%s\n');
fclose(notes_file);

notes(1) = [];
notes = [notes; {'Ssil'}];
[~, fs] = wavread(strcat(filename, '.wav'));

% convert sample to second
onsets = onsets./fs;

% lab = [onsets; 125; notes];
% lab = [onsets(1:end-1) onsets(2:end)];

output_dir = fullfile('lab', style);

if(~exist(output_dir, 'dir'))
    mkdir(output_dir);
end
% output_file = fopen('test_Aiken.lab', 'w');
% for i = 1: size(notes,1)
%     fprintf(output_file, '%f %f %s\n', lab(i, 1), lab(i, 2), notes{i});
% end
output_file = fopen(fullfile(output_dir, strcat(name, '.lab')), 'w');
for i = 1 : length(onsets)
    fprintf(output_file, '%f 125 %s\n', onsets(i), notes{i});
end
fclose(output_file);